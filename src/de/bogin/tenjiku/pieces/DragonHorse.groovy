package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class DragonHorse extends Piece {

	List<Move> getMoves(Board board, Square square) {
		List<Move> result
		result = movementRepository.rangesFrontLeft(board, square)
		result += movementRepository.rangesFrontRight(board, square)
		result += movementRepository.rangesBackLeft(board, square)
		result += movementRepository.rangesBackRight(board, square)
		
		result += movementRepository.stepsInFront(1, board, square)
		result += movementRepository.stepsBack(1, board, square)
		result += movementRepository.stepsLeft(1, board, square)
		result += movementRepository.stepsRight(1, board, square)
	}

}
