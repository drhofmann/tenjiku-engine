package de.bogin.tenjiku.board

import de.bogin.tenjiku.pieces.Piece
import de.bogin.tenjiku.pieces.PieceRepository
import de.bogin.tenjiku.pieces.PieceRepository.EvaluationScheme;
import de.bogin.tenjiku.pieces.PieceType
import de.bogin.tenjiku.pieces.PlayerColour

class BoardRepository {
	
	PieceRepository pieceRepository

	BoardRepository(EvaluationScheme evaluationScheme){
		pieceRepository = new PieceRepository(evaluationScheme: evaluationScheme)
	}
	
	Board getEmptyBoard(int width, int height){
		Board board = new Board(boardWidth: width, boardHeight: height)
		List<Square> squares = new ArrayList<Square>()

		(1..width).each{ xcoord ->
			(1..height).each{ ycoord ->
				Square square = new Square(x: xcoord, y: ycoord)
				squares << square
			}
		}
		board.squares = squares
		return board
	}
	
	Board getCopyOf(Board originalBoard){
		int origWidth = originalBoard.boardWidth
		int origHeight = originalBoard.boardHeight
		Board result = getEmptyBoard(origWidth, origHeight)
		
		List<Square> squaresWithPieces = originalBoard.getSquaresWithPieces()
		
		squaresWithPieces.each{
			Square copiedSquare = result.getSquareByCoordinates(it.x, it.y)
			Piece copiedPiece = pieceRepository.create(it.piece.type, it.piece.owner)
			copiedSquare.piece = copiedPiece
		}
		return result
	}
	
	Board getChuShogiInitialPosition(){
		Board chuBoard = getEmptyBoard(12,12)
		Piece blackPawn = pieceRepository.create(PieceType.PAWN, PlayerColour.BLACK)
		Piece blackGoBetween = pieceRepository.create(PieceType.PAWN, PlayerColour.BLACK)
		//TODO
	}
	
	Board getTenjikuShogiInitialPosition(){
		Board tenjikuBoard = getEmptyBoard(16,16)
		Piece blackPawn = pieceRepository.create(PieceType.PAWN, PlayerColour.BLACK)
		Piece blackDog = pieceRepository.create(PieceType.DOG, PlayerColour.BLACK)
		//TODO
		
		
		tenjikuBoard.putPieceOnSquare(blackDog, 12, 11)
		tenjikuBoard.putPieceOnSquare(blackDog, 5, 11)
		(1..16).each{
			tenjikuBoard.putPieceOnSquare(blackPawn, it, 12)
		}
	}
	
}
