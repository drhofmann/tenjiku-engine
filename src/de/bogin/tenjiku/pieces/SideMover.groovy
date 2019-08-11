package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class SideMover extends Piece {

	List<Move> getMoves(Board board, Square square) {
		List<Move> result
		result = movementRepository.stepsInFront(1, board, square)
		result += movementRepository.stepsBack(1, board, square)
		
		result += movementRepository.rangesLeft(board, square)
		result += movementRepository.rangesRight(board, square)
	}

}
