package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class Kirin extends Piece {

	List<Move> getMoves(Board board, Square square) {
		List<Move> result
		result =  movementRepository.stepsFrontLeft(1, board, square)
		result += movementRepository.stepsFrontRight(1, board, square)
		result += movementRepository.stepsBackLeft(1, board, square)
		result += movementRepository.stepsBackRight(1, board, square)
		
		result += movementRepository.jumpsInFront(2, board, square)
		result += movementRepository.jumpsBack(2, board, square)
		result += movementRepository.jumpsLeft(2, board, square)
		result += movementRepository.jumpsRight(2, board, square)
	}
}
