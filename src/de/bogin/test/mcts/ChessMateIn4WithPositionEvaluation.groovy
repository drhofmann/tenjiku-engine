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


public class ChessMateIn4WithPositionEvaluation extends BaseFindMoveScenario {

	
	ChessMateIn4WithPositionEvaluation(BoardRepository boardRepository, PieceRepository pieceRepository){
		this.boardRepository = boardRepository
		this.pieceRepository = pieceRepository
		numberOfSimulations = 80000
		minutesToWait = 50
	}
	
	Board setupBoard() {
		Board chessBoard = boardRepository.getEmptyBoard(8,8)

		Piece blackKing = pieceRepository.create(PieceType.KING,PlayerColour.BLACK)
		Piece blackRook = pieceRepository.create(PieceType.ROOK,PlayerColour.BLACK)
		Piece blackKnight = pieceRepository.create(PieceType.KNIGHT_CHESS,PlayerColour.BLACK)
		Piece blackQueen = pieceRepository.create(PieceType.FREE_KING,PlayerColour.BLACK)
		Piece blackPawn = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		
		Piece whiteKing = pieceRepository.create(PieceType.KING,PlayerColour.WHITE)
		Piece whiteRook = pieceRepository.create(PieceType.ROOK,PlayerColour.WHITE)
		Piece whiteKnight = pieceRepository.create(PieceType.KNIGHT_CHESS,PlayerColour.WHITE)
		Piece whiteBishop = pieceRepository.create(PieceType.BISHOP,PlayerColour.WHITE)
		Piece whiteQueen = pieceRepository.create(PieceType.FREE_KING,PlayerColour.WHITE)
		Piece whitePawn = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		

		chessBoard.putPieceOnSquare(blackKing, 2, 8)
		chessBoard.putPieceOnSquare(blackRook, 3, 8)
		chessBoard.putPieceOnSquare(blackRook, 2, 6)
		chessBoard.putPieceOnSquare(blackKnight, 4, 5)
		chessBoard.putPieceOnSquare(blackQueen, 2, 5)
		chessBoard.putPieceOnSquare(blackPawn, 8, 7)
		chessBoard.putPieceOnSquare(blackPawn, 6, 6)
		chessBoard.putPieceOnSquare(blackPawn, 5, 5)
		chessBoard.putPieceOnSquare(blackPawn, 3, 7)
		chessBoard.putPieceOnSquare(blackPawn, 2, 7)
		chessBoard.putPieceOnSquare(blackPawn, 1, 5)
		
		chessBoard.putPieceOnSquare(whiteKing, 3, 2)
		chessBoard.putPieceOnSquare(whiteRook, 8, 1)
		chessBoard.putPieceOnSquare(whiteRook, 5, 1)
		chessBoard.putPieceOnSquare(whiteKnight, 3, 1)
		chessBoard.putPieceOnSquare(whiteBishop, 4, 2)
		chessBoard.putPieceOnSquare(whiteQueen, 5, 4)
		chessBoard.putPieceOnSquare(whitePawn, 8, 2)
		chessBoard.putPieceOnSquare(whitePawn, 7, 2)
		chessBoard.putPieceOnSquare(whitePawn, 6, 3)
		chessBoard.putPieceOnSquare(whitePawn, 4, 3)
		chessBoard.putPieceOnSquare(whitePawn, 1, 3)
		
		return chessBoard
	}
	
	boolean checkSolution(tn) {
		tn.mostLikelySequence.first().toString()=="FK25-22"
	}
}