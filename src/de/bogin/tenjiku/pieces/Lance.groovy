package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class Lance extends Piece {

	List<Move> getMoves(Board board, Square square) {
		movementRepository.rangesInFront(board, square)
	}
}
