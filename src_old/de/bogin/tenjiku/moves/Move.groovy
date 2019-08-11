package de.bogin.tenjiku.moves

import de.bogin.tenjiku.board.Square
import de.bogin.tenjiku.pieces.Piece

class Move {
	Square initialSquare
	Square targetSquare
	Piece piece
	boolean takesAPiece
	
	String toString(){
		String moveSymbol
		if (takesAPiece) moveSymbol="x" else moveSymbol ="-"
		return "$piece.type.shortNotation$initialSquare.x$initialSquare.y$moveSymbol$targetSquare.x$targetSquare.y"
	}
}
