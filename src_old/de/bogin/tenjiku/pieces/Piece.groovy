package de.bogin.tenjiku.pieces

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.Square
import de.bogin.tenjiku.moves.Move
import de.bogin.tenjiku.moves.MovementRepository

abstract class Piece {

	MovementRepository movementRepository
	PieceType type
	PlayerColour owner
	
	abstract List<Move> getMoves(Board board, Square square)
	
	String toString(){
		"$owner $type"
	}
}
