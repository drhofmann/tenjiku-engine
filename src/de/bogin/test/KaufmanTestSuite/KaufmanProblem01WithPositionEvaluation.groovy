package de.bogin.test.KaufmanTestSuite;

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


public class KaufmanProblem01WithPositionEvaluation extends BaseFindMoveScenario {

	
	KaufmanProblem01WithPositionEvaluation(BoardRepository boardRepository, PieceRepository pieceRepository){
		this.boardRepository = boardRepository
		this.pieceRepository = pieceRepository
		numberOfSimulations = 8000
		minutesToWait = 10
	}
	
	Board setupBoard() {
		Board chessBoard = boardRepository.getEmptyBoard(8,8)

		Piece blackKing = pieceRepository.create(PieceType.KING,PlayerColour.BLACK)
		Piece blackRook = pieceRepository.create(PieceType.ROOK,PlayerColour.BLACK)
		Piece blackRook2 = pieceRepository.create(PieceType.ROOK,PlayerColour.BLACK)
		Piece blackKnight = pieceRepository.create(PieceType.KNIGHT_CHESS,PlayerColour.BLACK)
		Piece blackBishop = pieceRepository.create(PieceType.BISHOP,PlayerColour.BLACK)
		Piece blackBishop2 = pieceRepository.create(PieceType.BISHOP,PlayerColour.BLACK)
		Piece blackQueen = pieceRepository.create(PieceType.FREE_KING,PlayerColour.BLACK)
		Piece blackPawn = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		Piece blackPawn2 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		Piece blackPawn3 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		Piece blackPawn4 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		Piece blackPawn5 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.BLACK)
		
		Piece whiteKing = pieceRepository.create(PieceType.KING,PlayerColour.WHITE)
		Piece whiteRook = pieceRepository.create(PieceType.ROOK,PlayerColour.WHITE)
		Piece whiteRook2 = pieceRepository.create(PieceType.ROOK,PlayerColour.WHITE)
		Piece whiteKnight = pieceRepository.create(PieceType.KNIGHT_CHESS,PlayerColour.WHITE)
		Piece whiteBishop = pieceRepository.create(PieceType.BISHOP,PlayerColour.WHITE)
		Piece whiteBishop2 = pieceRepository.create(PieceType.BISHOP,PlayerColour.WHITE)
		Piece whiteQueen = pieceRepository.create(PieceType.FREE_KING,PlayerColour.WHITE)
		Piece whitePawn = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		Piece whitePawn2 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		Piece whitePawn3 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		Piece whitePawn4 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		Piece whitePawn5 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		Piece whitePawn6 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)
		Piece whitePawn7 = pieceRepository.create(PieceType.PAWN_CHESS,PlayerColour.WHITE)

		chessBoard.squares.find{it.x==1 && it.y==8}.piece=blackKing
		chessBoard.squares.find{it.x==3 && it.y==8}.piece=blackRook
		chessBoard.squares.find{it.x==6 && it.y==8}.piece=blackRook2
		chessBoard.squares.find{it.x==4 && it.y==5}.piece=blackKnight
		chessBoard.squares.find{it.x==5 && it.y==6}.piece=blackBishop
		chessBoard.squares.find{it.x==7 && it.y==5}.piece=blackBishop2
		chessBoard.squares.find{it.x==5 && it.y==8}.piece=blackQueen
		chessBoard.squares.find{it.x==1 && it.y==7}.piece=blackPawn
		chessBoard.squares.find{it.x==2 && it.y==7}.piece=blackPawn2
		chessBoard.squares.find{it.x==3 && it.y==7}.piece=blackPawn3
		chessBoard.squares.find{it.x==7 && it.y==7}.piece=blackPawn4
		chessBoard.squares.find{it.x==8 && it.y==6}.piece=blackPawn5
		
		chessBoard.squares.find{it.x==2 && it.y==1}.piece=whiteKing
		chessBoard.squares.find{it.x==3 && it.y==1}.piece=whiteRook
		chessBoard.squares.find{it.x==7 && it.y==1}.piece=whiteRook2
		chessBoard.squares.find{it.x==4 && it.y==2}.piece=whiteKnight
		chessBoard.squares.find{it.x==6 && it.y==1}.piece=whiteBishop
		chessBoard.squares.find{it.x==6 && it.y==2}.piece=whiteBishop2
		chessBoard.squares.find{it.x==5 && it.y==1}.piece=whiteQueen
		chessBoard.squares.find{it.x==1 && it.y==2}.piece=whitePawn
		chessBoard.squares.find{it.x==2 && it.y==2}.piece=whitePawn2
		chessBoard.squares.find{it.x==3 && it.y==2}.piece=whitePawn3
		chessBoard.squares.find{it.x==4 && it.y==3}.piece=whitePawn4
		chessBoard.squares.find{it.x==5 && it.y==5}.piece=whitePawn5
		chessBoard.squares.find{it.x==7 && it.y==3}.piece=whitePawn6
		chessBoard.squares.find{it.x==8 && it.y==2}.piece=whitePawn7
	chessBoard	
	}
	
	boolean checkSolution(tn) {
		tn.mostLikelySequence.first().toString()=="N45-33"
	}
}