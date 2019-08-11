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
import de.bogin.test.BaseFindMoveScenario;


public class ChessMateIn3WithPositionEvaluation extends BaseFindMoveScenario {
	
	ChessMateIn3WithPositionEvaluation(BoardRepository boardRepository, PieceRepository pieceRepository){
		this.boardRepository = boardRepository
		this.pieceRepository = pieceRepository
		numberOfSimulations = 8000
		minutesToWait = 10
	}
	
	Board setupBoard() {
		Board chessBoard = boardRepository.getEmptyBoard(8,8)

		Piece blackKing = pieceRepository.create(PieceType.KING,PlayerColour.BLACK)
		Piece blackRook = pieceRepository.create(PieceType.ROOK,PlayerColour.BLACK)
		Piece blackBishop = pieceRepository.create(PieceType.BISHOP,PlayerColour.BLACK)
		Piece blackPawn = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		Piece whiteKing = pieceRepository.create(PieceType.KING,PlayerColour.WHITE)
		Piece whiteRook = pieceRepository.create(PieceType.ROOK,PlayerColour.WHITE)
		Piece whiteRook2 = pieceRepository.create(PieceType.ROOK,PlayerColour.WHITE)
		Piece whitePawn = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		Piece whitePawn2 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		

		chessBoard.squares.find{it.x==1 && it.y==8}.piece=blackKing
		chessBoard.squares.find{it.x==3 && it.y==3}.piece=blackRook
		chessBoard.squares.find{it.x==4 && it.y==4}.piece=blackBishop
		chessBoard.squares.find{it.x==1 && it.y==7}.piece=blackPawn
		
		chessBoard.squares.find{it.x==1 && it.y==1}.piece=whiteKing
		chessBoard.squares.find{it.x==2 && it.y==1}.piece=whiteRook
		chessBoard.squares.find{it.x==8 && it.y==1}.piece=whiteRook2
		chessBoard.squares.find{it.x==1 && it.y==2}.piece=whitePawn
		chessBoard.squares.find{it.x==3 && it.y==2}.piece=whitePawn2
		return chessBoard
	}
	
	boolean checkSolution(tn) {
		tn.mostLikelySequence.first().toString()=="R33-83"
	}
}