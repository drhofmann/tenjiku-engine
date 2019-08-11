package de.bogin.tenjiku.pieces

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.Square
import de.bogin.tenjiku.moves.Move

class Pawn extends Piece {

	Pawn(){
		type = PieceType.PAWN
	}
	List<Move> getMoves(Board board, Square square) {
		movementRepository.stepsInFront(1, board, square)
	}

}
