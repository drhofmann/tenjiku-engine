package de.bogin.test.mcts;

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.BoardRepository
import de.bogin.tenjiku.mcts.ElapsedTimer
import de.bogin.tenjiku.mcts.TreeNodeWithPositionEvaluation
import de.bogin.tenjiku.pieces.Piece
import de.bogin.tenjiku.pieces.PieceRepository
import de.bogin.tenjiku.pieces.PieceType
import de.bogin.tenjiku.pieces.PlayerColour
import de.bogin.tenjiku.pieces.PieceRepository.EvaluationScheme
import de.bogin.tenjiku.position.BoardPosition


public class RookKingVsKingBiggerBoardWithPositionEvaluation {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoardRepository boardRepository = new BoardRepository(EvaluationScheme.CHESS)
		PieceRepository pieceRepository = boardRepository.pieceRepository
		Board chessBoard = boardRepository.getEmptyBoard(8,8)

		Piece blackKing = pieceRepository.create(PieceType.KING,PlayerColour.BLACK)
		Piece blackRook = pieceRepository.create(PieceType.ROOK,PlayerColour.BLACK)
		Piece whiteKing = pieceRepository.create(PieceType.KING,PlayerColour.WHITE)

		chessBoard.squares.find{it.x==5 && it.y==5}.piece=blackKing
		chessBoard.squares.find{it.x==1 && it.y==2}.piece=blackRook
		chessBoard.squares.find{it.x==8 && it.y==1}.piece=whiteKing

		PlayerColour colour = PlayerColour.BLACK

		BoardPosition start = new BoardPosition(chessBoard, colour, boardRepository)
		TreeNodeWithPositionEvaluation tn = new TreeNodeWithPositionEvaluation(position: start);

		println start.evaluate()
		
		println start.generateSensibleMovesForActivePlayer()
		ElapsedTimer t = new ElapsedTimer();
		int n = 22000;
		int numberOfMinutes = 10
		int i = 0
		while(i<n){
			tn.selectAction();
			if (t.elapsed()>numberOfMinutes*60000){
				break
			}
			i++
			if(i % 1000==0){
				tn.flatten()
				println "Most likely sequence after $i simulations (${t.elapsed() / 1000} sec):"
				println "$tn.mostLikelySequence - score: $tn.scoreForMostLikelySequence"
			}
		}
		
		System.out.println(t);
		System.out.println("Done");
		println tn.totValue
		println tn.nVisits
		println "Average score (for black): ${tn.totValue/tn.nVisits}"
		tn.children.each{
			if (it.nVisits>0)
				println "Score after $it.position.lastMove : ${it.totValue/it.nVisits} - $it.totValue / $it.nVisits"
		}
		//TreeNodeWithPositionEvaluation k64_73node = tn.children.find{it.position.lastMove.toString()=='K64-73'}
		tn.flatten()
		println tn.mostLikelySequence
		println tn.scoreForMostLikelySequence

		/*println k64_73node.mostLikelySequence
		println k64_73node.totValue/k64_73node.nVisits
		k64_73node.children.each{
			if (it.nVisits>0)
				println "Score after $it.position.lastMove : ${it.totValue/it.nVisits} - $it.totValue / $it.nVisits"
		}
		
		TreeNodeWithPositionEvaluation lastNode = k64_73node.children.first()
		
		lastNode.children.each{
			if (it.nVisits>0)
			println "Score after $it.position.lastMove : ${it.totValue/it.nVisits} - $it.totValue / $it.nVisits"
		}

		println "After move R12-11"
		TreeNodeWithPositionEvaluation lastNode2 = lastNode.children.find{it.position.lastMove.toString()=="R12-11"}
		
		lastNode2.children.each{
			if (it.nVisits>0)
			println "Score after $it.position.lastMove : ${it.totValue/it.nVisits} - $it.totValue / $it.nVisits"
		}
		*/
		println "UCT values for direct daughter nodes:"
		tn.children.each{
			if (it.nVisits&&it.totValue) {
				double uctValue = tn.calculateUctValue(it)
				println "UCT value for move $it.position.lastMove : $uctValue"
			}
		}
		assert ["K55-64", "K55-54"].contains(tn.mostLikelySequence.first().toString())
	}
}