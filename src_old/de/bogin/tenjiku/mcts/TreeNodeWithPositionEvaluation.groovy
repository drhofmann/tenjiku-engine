package de.bogin.tenjiku.mcts;

import de.bogin.tenjiku.moves.Move
import de.bogin.tenjiku.pieces.PieceType
import de.bogin.tenjiku.pieces.PlayerColour
import de.bogin.tenjiku.position.BoardPosition

public class TreeNodeWithPositionEvaluation {
	BoardPosition position
	static Random r = new Random();
	static double epsilon = 1e-6;
	List<Move> mostLikelySequence = new ArrayList<Move>()
	double scoreForMostLikelySequence = 0
	double totValue

	List<TreeNodeWithPositionEvaluation> children;
	int nVisits

	public void selectAction() {
		eliminateBadChildren()
		List<TreeNodeWithPositionEvaluation> visited = new LinkedList<TreeNodeWithPositionEvaluation>();
		TreeNodeWithPositionEvaluation cur = this;
		visited.add(this);
		while (!cur.isLeaf()) {
			cur = cur.select();
			visited.add(cur);
		}
		cur.expand();
		TreeNodeWithPositionEvaluation newNode = cur.select();
		if (newNode) {
			visited.add(newNode)
		}
		else {
			newNode = cur
		}
		double result = 0.0
		Move selectedMove = newNode.position.lastMove
		if (!cur.children && cur.position.getSquaresAttackedBy(cur.position.playerToMove.getOpposingColour()).contains(cur.position.getSquareWithKingBy(cur.position.playerToMove))) {
			if (cur.position.playerToMove == PlayerColour.WHITE){
				result = result + 1000
			} else {
				result = result - 1000
			}
		} else {
			result += cur.position.evaluate()
		}
		for (TreeNodeWithPositionEvaluation node : visited) {
			// would need extra logic for n-player game
			// System.out.println(node);
			node.updateStats(result)
		}
	}

	public void expand() {
		List<Move> moves = position.generateSensibleMovesForActivePlayer()
		if (!moves) {
			return null
		}
		int numberOfChildren = moves.size()
		children = new ArrayList<TreeNodeWithPositionEvaluation>()
		moves.each{
			BoardPosition newPosition = position.getNewPositionAfterMove(it)
			children << new TreeNodeWithPositionEvaluation(position: newPosition)
		}
	}

	private TreeNodeWithPositionEvaluation select() {
		TreeNodeWithPositionEvaluation selected = null;
		double bestValue = -Double.MAX_VALUE;
		children.each{ c->
			double uctValue = calculateUctValue(c)
			if (uctValue > bestValue) {
				selected = c;
				bestValue = uctValue;
			}
		}
		return selected;
	}

	public boolean isLeaf() {
		return children == null;
	}

	public double rollOut(TreeNodeWithPositionEvaluation tn) {
		// ultimately a roll out will end in some value
		// assume for now that it ends in a win or a loss
		// and just return this at random
		return r.nextInt(2);
	}

	public void updateStats(double value) {
		nVisits++
		totValue += value
	}

	public int arity() {
		return children.size();
	}

	def flatten(){
		children.each{ it.flatten() }
		if (!children){
			if (nVisits) {
				scoreForMostLikelySequence=totValue/nVisits
			}
			return
		}
		if (position.playerToMove==PlayerColour.BLACK){
			scoreForMostLikelySequence = children.collect{ it.scoreForMostLikelySequence }.max()
		} else {
			scoreForMostLikelySequence = children.collect{ it.scoreForMostLikelySequence }.min()
		}
		TreeNodeWithPositionEvaluation mostLikelyChildNode = children.find{ it.scoreForMostLikelySequence==scoreForMostLikelySequence }
		mostLikelySequence=mostLikelyChildNode.mostLikelySequence
		mostLikelySequence.add(0,mostLikelyChildNode.position.lastMove)
	}

	double calculateUctValue(TreeNodeWithPositionEvaluation child){
		double uctValue
		if (position.playerToMove==PlayerColour.BLACK){
			uctValue =
					child.totValue / (child.nVisits + epsilon) +
					100*Math.sqrt(Math.log(nVisits+1) / (child.nVisits + epsilon)) +
					r.nextDouble() * epsilon
		} else {
			uctValue =
					(- child.totValue) / (child.nVisits + epsilon) +
					Math.sqrt(Math.log(nVisits+1) / (child.nVisits + epsilon)) +
					r.nextDouble() * epsilon
		}
		return uctValue
	}

	void eliminateBadChildren(){
		List<TreeNodeWithPositionEvaluation> badChildren = new ArrayList<TreeNodeWithPositionEvaluation>()
		if (position.playerToMove==PlayerColour.BLACK){
			children.each{
				if (it.nVisits>=100 && ((totValue/nVisits-it.totValue/it.nVisits) > 4)) {
					badChildren << it
				}
			}
		}
		else {
			children.each{
				if (it.nVisits>=100 && ((totValue/nVisits-it.totValue/it.nVisits) < -4)) {
					badChildren << it
				}
			}
		}
		badChildren.each{it -> children.remove(it)
		println "Removed node $it.position.lastMove with score ${it.totValue/it.nVisits}"}
	}
}