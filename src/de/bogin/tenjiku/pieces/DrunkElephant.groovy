package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class DrunkElephant extends Piece {

	List<Move> getMoves(Board board, Square square) {
		List<Move> result
		result = movementRepository.stepsInFront(1, board, square)
		result += movementRepository.stepsLeft(1, board, square)
		result += movementRepository.stepsRight(1, board, square)
		result += movementRepository.stepsFrontLeft(1, board, square)
		result += movementRepository.stepsFrontRight(1, board, square)
		result += movementRepository.stepsBackLeft(1, board, square)
		result += movementRepository.stepsBackRight(1, board, square)
	}

}
