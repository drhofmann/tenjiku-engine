package de.bogin.tenjiku.board

import de.bogin.tenjiku.pieces.Piece
import de.bogin.tenjiku.pieces.PieceRepository

class BoardRepository {
	
	PieceRepository pieceRepository = new PieceRepository()

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
}
