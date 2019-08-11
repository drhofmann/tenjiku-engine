package de.bogin.tenjiku.board

import de.bogin.tenjiku.pieces.Piece

class Square {
	//in a view where sente is at the bottom and gote at the top (top right = (1,1), top left = (n,1))
	int x
	int y
	
	Piece piece = null
	
	String toString(){
		String pieceString = piece?:""
		"($x,$y) $pieceString"
	}
}
