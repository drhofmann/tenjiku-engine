package de.bogin.test.mcts;

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.BoardRepository
import de.bogin.tenjiku.mcts.ElapsedTimer
import de.bogin.tenjiku.mcts.TreeNodeWithPositionEvaluation
import de.bogin.tenjiku.mcts.TreeView
import de.bogin.tenjiku.pieces.Piece
import de.bogin.tenjiku.pieces.PieceRepository
import de.bogin.tenjiku.pieces.PieceType
import de.bogin.tenjiku.pieces.PlayerColour
import de.bogin.tenjiku.position.BoardPosition


public class ChessMateIn4WithPositionEvaluation {

	public static void main(String[] args) {
		BoardRepository boardRepository = new BoardRepository()
		PieceRepository pieceRepository = new PieceRepository()
		Board chessBoard = boardRepository.getEmptyBoard(8,8)

		Piece blackKing = pieceRepository.create(PieceType.KING,PlayerColour.BLACK)
		Piece blackRook = pieceRepository.create(PieceType.ROOK,PlayerColour.BLACK)
		Piece blackRook2 = pieceRepository.create(PieceType.ROOK,PlayerColour.BLACK)
		Piece blackKnight = pieceRepository.create(PieceType.KNIGHT_CHESS,PlayerColour.BLACK)
		Piece blackQueen = pieceRepository.create(PieceType.FREEKING,PlayerColour.BLACK)
		Piece blackPawn = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		Piece blackPawn2 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		Piece blackPawn3 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		Piece blackPawn4 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		Piece blackPawn5 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		Piece blackPawn6 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		
		
		Piece whiteKing = pieceRepository.create(PieceType.KING,PlayerColour.WHITE)
		Piece whiteRook = pieceRepository.create(PieceType.ROOK,PlayerColour.WHITE)
		Piece whiteRook2 = pieceRepository.create(PieceType.ROOK,PlayerColour.WHITE)
		Piece whiteKnight = pieceRepository.create(PieceType.KNIGHT_CHESS,PlayerColour.WHITE)
		Piece whiteBishop = pieceRepository.create(PieceType.BISHOP,PlayerColour.WHITE)
		Piece whiteQueen = pieceRepository.create(PieceType.FREEKING,PlayerColour.WHITE)
		Piece whitePawn = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		Piece whitePawn2 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		Piece whitePawn3 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		Piece whitePawn4 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		Piece whitePawn5 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		

		chessBoard.squares.find{it.x==2 && it.y==8}.piece=blackKing
		chessBoard.squares.find{it.x==3 && it.y==8}.piece=blackRook
		chessBoard.squares.find{it.x==2 && it.y==6}.piece=blackRook2
		chessBoard.squares.find{it.x==4 && it.y==5}.piece=blackKnight
		chessBoard.squares.find{it.x==2 && it.y==5}.piece=blackQueen
		chessBoard.squares.find{it.x==8 && it.y==7}.piece=blackPawn
		chessBoard.squares.find{it.x==6 && it.y==6}.piece=blackPawn2
		chessBoard.squares.find{it.x==5 && it.y==5}.piece=blackPawn3
		chessBoard.squares.find{it.x==3 && it.y==7}.piece=blackPawn4
		chessBoard.squares.find{it.x==2 && it.y==7}.piece=blackPawn5
		chessBoard.squares.find{it.x==1 && it.y==5}.piece=blackPawn6
		
		chessBoard.squares.find{it.x==3 && it.y==2}.piece=whiteKing
		chessBoard.squares.find{it.x==8 && it.y==1}.piece=whiteRook
		chessBoard.squares.find{it.x==5 && it.y==1}.piece=whiteRook2
		chessBoard.squares.find{it.x==3 && it.y==1}.piece=whiteKnight
		chessBoard.squares.find{it.x==4 && it.y==2}.piece=whiteBishop
		chessBoard.squares.find{it.x==5 && it.y==4}.piece=whiteQueen
		chessBoard.squares.find{it.x==8 && it.y==2}.piece=whitePawn
		chessBoard.squares.find{it.x==7 && it.y==2}.piece=whitePawn2
		chessBoard.squares.find{it.x==6 && it.y==3}.piece=whitePawn3
		chessBoard.squares.find{it.x==4 && it.y==3}.piece=whitePawn4
		chessBoard.squares.find{it.x==1 && it.y==3}.piece=whitePawn5
		
		PlayerColour colour = PlayerColour.BLACK

		BoardPosition start = new BoardPosition(chessBoard, colour)
		TreeNodeWithPositionEvaluation tn = new TreeNodeWithPositionEvaluation(position: start);

		println start.evaluate()
		
		println start.generateSensibleMovesForActivePlayer()
		ElapsedTimer t = new ElapsedTimer();
		int n = 9000;
		int numberOfMinutes = 8
		int i = 0
		while(i<n){
			tn.selectAction();
			if (t.elapsed()>numberOfMinutes*60000){
				break
			}
			i++
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
		assert tn.mostLikelySequence.first().toString()=="FK25-22"
	}
}