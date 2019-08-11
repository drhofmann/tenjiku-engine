package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class FreeKing extends Piece {

	List<Move> getMoves(Board board, Square square) {
		List<Move> result
		result = movementRepository.rangesInFront(board, square)
		result += movementRepository.rangesBack(board, square)
		result += movementRepository.rangesLeft(board, square)
		result += movementRepository.rangesRight(board, square)
		result += movementRepository.rangesFrontLeft(board, square)
		result += movementRepository.rangesFrontRight(board, square)
		result += movementRepository.rangesBackLeft(board, square)
		result += movementRepository.rangesBackRight(board, square)
	}

}
