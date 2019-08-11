package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class CopperGeneral extends Piece {

	List<Move> getMoves(Board board, Square square) {
		List<Move> result
		result = movementRepository.stepsInFront(1, board, square)
		result += movementRepository.stepsBack(1, board, square)
		result += movementRepository.stepsFrontLeft(1, board, square)
		result += movementRepository.stepsFrontRight(1, board, square)
	}

}
