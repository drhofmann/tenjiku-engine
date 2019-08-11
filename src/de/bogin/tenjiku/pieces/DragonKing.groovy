package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class DragonKing extends Piece {

	List<Move> getMoves(Board board, Square square) {
		List<Move> result
		result = movementRepository.rangesInFront(board, square)
		result += movementRepository.rangesBack(board, square)
		result += movementRepository.rangesLeft(board, square)
		result += movementRepository.rangesRight(board, square)
		
		result += movementRepository.stepsFrontLeft(1, board, square)
		result += movementRepository.stepsFrontRight(1, board, square)
		result += movementRepository.stepsBackLeft(1, board, square)
		result += movementRepository.stepsBackRight(1, board, square)
	}

}
