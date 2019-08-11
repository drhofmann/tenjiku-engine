package de.bogin.tenjiku.pieces;

public enum PieceType {
	PAWN("P"), DOG("D"), KING("K"), ROOK("R"), BISHOP("B"), KNIGHT("N"),
	FREE_KING("FK"), KNIGHT_CHESS("N"), PAWN_CHESS("P"), GOBETWEEN("GB"),
	COPPER_GENERAL("C"), SILVER_GENERAL("S"), BLIND_TIGER("BT"), DRUNK_ELEPHANT("DE"),
	FEROCIOUS_LEOPARD("FL"), GOLD_GENERAL("G"), DRAGON_HORSE("DH"), DRAGON_KING("DK"),
	SIDE_MOVER("SM"), VERTICAL_MOVER("VM"), LANCE("L"), REVERSE_CHARIOT("RC"),
	PHOENIX("Ph"), KIRIN("Kr"), LION_CHU("Ln"), LION_TENJIKU("Ln")
	
	private final String shortNotation
	PieceType(String shortNotation) {this.shortNotation = shortNotation
	}
}
