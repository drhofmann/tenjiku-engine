package de.bogin.tenjiku.mcts;

import de.bogin.tenjiku.moves.Move
import de.bogin.tenjiku.pieces.PieceType
import de.bogin.tenjiku.pieces.PlayerColour
import de.bogin.tenjiku.position.BoardPosition

public class TreeNode {
	BoardPosition position
	static Random r = new Random();
	static double epsilon = 1e-6;
	int numberOfPlayOutsPerVisit = 10
	List<Move> mostLikelySequence = new ArrayList<Move>()
	double blackWinrateForMostLikelySequence = 0

	List<TreeNode> children;
	int nVisits, totValue;

	public void selectAction() {
		eliminateBadChildren()
		List<TreeNode> visited = new LinkedList<TreeNode>();
		TreeNode cur = this;
		visited.add(this);
		while (!cur.isLeaf()) {
			cur = cur.select();
			visited.add(cur);
		}
		cur.expand();
		TreeNode newNode = cur.select();
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
				result = result + numberOfPlayOutsPerVisit
			}
		} else {
			numberOfPlayOutsPerVisit.times{
				PlayOut playout = new PlayOut(initialPosition: newNode.position)
				double res = playout.run()
				result += res
			}
		}
		for (TreeNode node : visited) {
			// would need extra logic for n-player game
			// System.out.println(node);
			node.updateStats(result)
		}
	}

	public void expand() {
		List<Move> moves = position.generateSensibleMovesForActivePlayer()
		if (!moves) {
			return null
			println "no moves"
		}
		int numberOfChildren = moves.size()
		children = new ArrayList<TreeNode>()
		moves.each{
			BoardPosition newPosition = position.getNewPositionAfterMove(it)
			children << new TreeNode(position: newPosition)
		}
	}

	private TreeNode select() {
		TreeNode selected = null;
		double bestValue = Double.MIN_VALUE;
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

	public double rollOut(TreeNode tn) {
		// ultimately a roll out will end in some value
		// assume for now that it ends in a win or a loss
		// and just return this at random
		return r.nextInt(2);
	}

	public void updateStats(double value) {
		nVisits+=numberOfPlayOutsPerVisit;
		totValue += value;
	}

	public int arity() {
		return children == null ? 0 : children.length;
	}

	def flatten(){
		children.each{ it.flatten() }
		if (!children){
			if (nVisits) {
				blackWinrateForMostLikelySequence=totValue/nVisits
			}
			return
		}
		if (position.playerToMove==PlayerColour.BLACK){
			blackWinrateForMostLikelySequence = children.collect{ it.blackWinrateForMostLikelySequence }.max()
		} else {
			blackWinrateForMostLikelySequence = children.collect{ it.blackWinrateForMostLikelySequence }.min()
		}
		TreeNode mostLikelyChildNode = children.find{ it.blackWinrateForMostLikelySequence==blackWinrateForMostLikelySequence }
		mostLikelySequence=mostLikelyChildNode.mostLikelySequence
		mostLikelySequence.add(0,mostLikelyChildNode.position.lastMove)
	}

	double calculateUctValue(TreeNode child){
		double uctValue
		if (position.playerToMove==PlayerColour.BLACK){
			uctValue =
					child.totValue / (child.nVisits + epsilon) +
					Math.sqrt(Math.log(nVisits+1) / (child.nVisits + epsilon)) +
					r.nextDouble() * epsilon
		} else {
			uctValue =
					(child.nVisits - child.totValue) / (child.nVisits + epsilon) +
					Math.sqrt(Math.log(nVisits+1) / (child.nVisits + epsilon)) +
					r.nextDouble() * epsilon
		}
		return uctValue
	}

	void eliminateBadChildren(){
		List<TreeNode> badChildren = new ArrayList<TreeNode>()
		if (position.playerToMove==PlayerColour.BLACK){
			children.each{
				if (it.nVisits>=10 && it.totValue/it.nVisits < 0.3) {
					badChildren << it
				}
			}
		}
		else {
			children.each{
				if (it.nVisits>=10 && it.totValue/it.nVisits > 0.7) {
					badChildren << it
				}
			}
		}
		badChildren.each{it -> children.remove(it)
		println "Removed node $it.position.lastMove with black winrate ${it.totValue/it.nVisits}"}
	}
}