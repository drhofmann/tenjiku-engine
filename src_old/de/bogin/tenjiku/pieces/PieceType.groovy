package de.bogin.tenjiku.pieces;

public enum PieceType {
	PAWN("P",1), DOG("D",1), KING("K",1000), ROOK("R",12), BISHOP("B",10), KNIGHT("N",1), FREEKING("FK",22), KNIGHT_CHESS("N",10), PAWN_CHESS("P",1)
	
	private final String shortNotation
	private final double value
	PieceType(String shortNotation, double value) {this.shortNotation = shortNotation
		this.value = value
	}
}
