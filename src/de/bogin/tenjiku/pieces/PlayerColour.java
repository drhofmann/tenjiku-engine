package de.bogin.tenjiku.pieces;

public enum PlayerColour {
	BLACK, WHITE;
	
	PlayerColour getOpposingColour(){
		PlayerColour result=null;
		if (this == BLACK) result = PlayerColour.WHITE;
		else if (this == WHITE) result = PlayerColour.BLACK;
		return result;
	}
}
