package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class VerticalMover extends Piece {

	List<Move> getMoves(Board board, Square square) {
		List<Move> result
		result = movementRepository.rangesInFront(board, square)
		result += movementRepository.rangesBack(board, square)
		
		result += movementRepository.stepsLeft(1, board, square)
		result += movementRepository.stepsRight(1, board, square)
	}

}
