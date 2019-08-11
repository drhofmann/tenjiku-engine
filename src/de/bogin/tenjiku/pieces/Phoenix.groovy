package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class Phoenix extends Piece {

	List<Move> getMoves(Board board, Square square) {
		List<Move> result
		result = movementRepository.stepsInFront(1, board, square)
		result += movementRepository.stepsBack(1, board, square)
		result += movementRepository.stepsLeft(1, board, square)
		result += movementRepository.stepsRight(1, board, square)
		
		result += movementRepository.jumpsFrontLeft(2, board, square)
		result += movementRepository.jumpsFrontRight(2, board, square)
		result += movementRepository.jumpsBackLeft(2, board, square)
		result += movementRepository.jumpsBackRight(2, board, square)
	}

}
