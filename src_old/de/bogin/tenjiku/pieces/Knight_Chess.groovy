package de.bogin.tenjiku.pieces

import java.util.List

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.Square;
import de.bogin.tenjiku.moves.Move;

class Knight_Chess extends Piece {

	Knight_Chess(){
		type = PieceType.KNIGHT_CHESS
	}
	List<Move> getMoves(Board board, Square square) {
		List<Move> result
		result = movementRepository.knightJumpsInFront(board, square)
		result += movementRepository.knightJumpsBack(board, square)
		result += movementRepository.knightJumpsLeft(board, square)
		result += movementRepository.knightJumpsRight(board, square)
	}

}
