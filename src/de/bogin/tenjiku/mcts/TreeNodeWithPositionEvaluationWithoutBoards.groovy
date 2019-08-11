package de.bogin.tenjiku.mcts;

import de.bogin.tenjiku.moves.Move
import de.bogin.tenjiku.pieces.PieceType
import de.bogin.tenjiku.pieces.PlayerColour
import de.bogin.tenjiku.position.BoardPosition

public class TreeNodeWithPositionEvaluationWithoutBoards {
	static Random r = new Random();
	static double epsilon = 1e-6;
	static double badChildrenThreshold = 2
	List<Move> mostLikelySequence = []
	double scoreForMostLikelySequence = 0
	double totValue
	PlayerColour playerToMove
	Move lastMove

	List<TreeNodeWithPositionEvaluationWithoutBoards> children;
	int nVisits

	public void selectAction(BoardPosition initialPosition) {
		BoardPosition currentPosition = new BoardPosition(initialPosition.boardRepository.getCopyOf(initialPosition.board), playerToMove, initialPosition.boardRepository)
		List<TreeNodeWithPositionEvaluationWithoutBoards> visited = new LinkedList<TreeNodeWithPositionEvaluationWithoutBoards>();
		TreeNodeWithPositionEvaluationWithoutBoards cur = this;
		visited.add(this);
		while (!cur.isLeaf()) {
			cur.eliminateBadChildren()
			cur = cur.select();
			currentPosition.makeMove(cur.lastMove)
			visited.add(cur);
		}
		cur.expand(currentPosition);
		TreeNodeWithPositionEvaluationWithoutBoards newNode = cur.select();
		if (newNode) {
			visited.add(newNode)
			currentPosition.makeMove(newNode.lastMove)
		}
		else {
			newNode = cur
		}
		double result = 0.0
		Move selectedMove = newNode.lastMove
		if (!cur.children && currentPosition.getSquaresAttackedBy(currentPosition.playerToMove.getOpposingColour()).contains(currentPosition.getSquareWithKingBy(currentPosition.playerToMove))) {
			if (cur.playerToMove == PlayerColour.WHITE){
				result = result + 1000
			} else {
				result = result - 1000
			}
		} else {
			result += currentPosition.evaluate()
		}
		for (TreeNodeWithPositionEvaluationWithoutBoards node : visited) {
			// would need extra logic for n-player game
			// System.out.println(node);
			node.updateStats(result)
		}
	}

	public void expand(BoardPosition currentPosition) {
		List<Move> moves = currentPosition.generateSensibleMovesForActivePlayer()
		if (!moves) {
			return null
		}
		children = new ArrayList<TreeNodeWithPositionEvaluationWithoutBoards>()
		moves.each{
			children << new TreeNodeWithPositionEvaluationWithoutBoards(lastMove: it, playerToMove:this.playerToMove.opposingColour)
		}
	}

	private TreeNodeWithPositionEvaluationWithoutBoards select() {
		TreeNodeWithPositionEvaluationWithoutBoards selected = null;
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

	public void updateStats(double value) {
		nVisits++
		totValue += value
	}

	public int arity() {
		return children.size();
	}

	def flatten(){
		mostLikelySequence = []
		children.each{ it.flatten() }
		if (!children){
			if (nVisits) {
				scoreForMostLikelySequence=totValue/nVisits
			}
			return
		}
		if (playerToMove==PlayerColour.BLACK){
			scoreForMostLikelySequence = children.collect{ it.scoreForMostLikelySequence }.max()
		} else {
			scoreForMostLikelySequence = children.collect{ it.scoreForMostLikelySequence }.min()
		}
		TreeNodeWithPositionEvaluationWithoutBoards mostLikelyChildNode = children.find{ it.scoreForMostLikelySequence==scoreForMostLikelySequence }
		mostLikelySequence=mostLikelyChildNode.mostLikelySequence
		mostLikelySequence.add(0,mostLikelyChildNode.lastMove)
	}

	double calculateUctValue(TreeNodeWithPositionEvaluationWithoutBoards child){
		double uctValue
		if (playerToMove==PlayerColour.BLACK){
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
		List<TreeNodeWithPositionEvaluationWithoutBoards> badChildren = new ArrayList<TreeNodeWithPositionEvaluationWithoutBoards>()
		if (playerToMove==PlayerColour.BLACK){
			children.each{
				if (it.nVisits>=100 && ((totValue/nVisits-it.totValue/it.nVisits) > badChildrenThreshold)) {
					badChildren << it
				}
			}
		}
		else {
			children.each{
				if (it.nVisits>=100 && ((totValue/nVisits-it.totValue/it.nVisits) < -badChildrenThreshold)) {
					badChildren << it
				}
			}
		}
		badChildren.each{it -> children.remove(it)
		println "Removed node $it.lastMove with score ${it.totValue/it.nVisits}"}
	}
}