package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class Bishop extends Piece {

	List<Move> getMoves(Board board, Square square) {
		List<Move> result
		result = movementRepository.rangesFrontLeft(board, square)
		result += movementRepository.rangesFrontRight(board, square)
		result += movementRepository.rangesBackLeft(board, square)
		result += movementRepository.rangesBackRight(board, square)
	}

}
