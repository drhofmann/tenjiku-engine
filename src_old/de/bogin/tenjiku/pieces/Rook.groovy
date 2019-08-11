package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class Rook extends Piece {

	Rook(){
		type = PieceType.ROOK
	}
	List<Move> getMoves(Board board, Square square) {
		List<Move> result
		result = movementRepository.rangesInFront(board, square)
		result += movementRepository.rangesBack(board, square)
		result += movementRepository.rangesLeft(board, square)
		result += movementRepository.rangesRight(board, square)
	}

}
