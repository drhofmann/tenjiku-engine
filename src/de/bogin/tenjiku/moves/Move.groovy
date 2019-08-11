package de.bogin.tenjiku.moves

import de.bogin.tenjiku.board.Square
import de.bogin.tenjiku.pieces.Piece

class Move {
	Square initialSquare
	Square targetSquare
	Square targetSquareForSecondLionMove
	Piece piece
	boolean takesAPiece
	boolean takesAPieceOnSecondLionMove
	boolean igui
	
	String toString(){
		String moveSymbol, moveSymbol2, stringForSecondMove
		if (takesAPiece) moveSymbol="x" else moveSymbol ="-"
		if (igui) moveSymbol +="!"
		
		if(targetSquareForSecondLionMove){
			if (takesAPieceOnSecondLionMove) moveSymbol2="x" else moveSymbol2 ="-"
			stringForSecondMove="$moveSymbol2$targetSquareForSecondLionMove.x$targetSquareForSecondLionMove.y"
		} else {
		stringForSecondMove=""
		}
		return "$piece.type.shortNotation$initialSquare.x$initialSquare.y$moveSymbol$targetSquare.x$targetSquare.y$stringForSecondMove"
	}
}
