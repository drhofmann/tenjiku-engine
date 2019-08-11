package de.bogin.test.mcts;

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.BoardRepository
import de.bogin.tenjiku.mcts.TreeNodeWithPositionEvaluation
import de.bogin.tenjiku.pieces.Piece
import de.bogin.tenjiku.pieces.PieceRepository
import de.bogin.tenjiku.pieces.PieceType
import de.bogin.tenjiku.pieces.PlayerColour
import de.bogin.test.BaseFindMoveScenario;
import de.bogin.test.FindMoveScenario


public class DummyTest2 extends BaseFindMoveScenario {

	DummyTest2(BoardRepository boardRepository, PieceRepository pieceRepository){
		this.boardRepository = boardRepository
		this.pieceRepository = pieceRepository
		numberOfSimulations = 8000
		minutesToWait = 10
	}
	
	Board setupBoard() {
		Board board = boardRepository.getEmptyBoard(8,8)
		
		Piece blackKing = pieceRepository.create(PieceType.KING,PlayerColour.BLACK)
		Piece blackRook = pieceRepository.create(PieceType.ROOK,PlayerColour.BLACK)
		Piece blackBishop = pieceRepository.create(PieceType.BISHOP,PlayerColour.BLACK)
		Piece blackPawn = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		Piece whiteKing = pieceRepository.create(PieceType.KING,PlayerColour.WHITE)
		Piece whiteRook = pieceRepository.create(PieceType.ROOK,PlayerColour.WHITE)
		Piece whiteRook2 = pieceRepository.create(PieceType.ROOK,PlayerColour.WHITE)
		Piece whitePawn = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		Piece whitePawn2 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		

		board.squares.find{it.x==1 && it.y==8}.piece=blackKing
		board.squares.find{it.x==3 && it.y==3}.piece=blackRook
		board.squares.find{it.x==4 && it.y==4}.piece=blackBishop
		board.squares.find{it.x==1 && it.y==7}.piece=blackPawn
		
		board.squares.find{it.x==1 && it.y==1}.piece=whiteKing
		board.squares.find{it.x==2 && it.y==1}.piece=whiteRook
		board.squares.find{it.x==8 && it.y==1}.piece=whiteRook2
		board.squares.find{it.x==1 && it.y==2}.piece=whitePawn
		board.squares.find{it.x==3 && it.y==2}.piece=whitePawn2
		return board
	}
	
	boolean checkSolution(tn) {
		tn.mostLikelySequence.first().toString()=="R33-83"
	}
}