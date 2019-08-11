package de.bogin.tenjiku.pieces

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.Square
import de.bogin.tenjiku.moves.Move

class GoBetween extends Piece {

	List<Move> getMoves(Board board, Square square) {
		List<Move> result = movementRepository.stepsInFront(1, board, square)
		result += movementRepository.stepsBack(1, board, square)
	}
}
