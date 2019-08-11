package de.bogin.tenjiku.pieces

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.Square
import de.bogin.tenjiku.moves.Move

class Pawn_Chess extends Piece {

	List<Move> getMoves(Board board, Square square) {
		movementRepository.chessPawnMove(board, square)
	}

}
