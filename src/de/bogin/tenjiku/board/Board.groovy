package de.bogin.tenjiku.board

import de.bogin.tenjiku.moves.Move
import de.bogin.tenjiku.pieces.Piece
import de.bogin.tenjiku.pieces.PlayerColour

class Board {
	
	int boardWidth
	int boardHeight
	
	List<Square> squares = new ArrayList<Square>()

	
	
	List<Square> getSquaresWithPieces(){
		squares.findAll{it.piece}
	}
	
	List<Square> getSquaresWithPiecesOf(PlayerColour colour){
		squares.findAll{it.piece?.owner==colour}
	}

	List<Move> generateMovesFor(PlayerColour colour){
		List<Move> moveCandidates = new ArrayList<Move>()
		List<Square> squaresWithPieces = getSquaresWithPiecesOf(colour)
		squaresWithPieces.each{
			square ->
			List<Move> moves = square.piece.getMoves(this, square)
			moves.each{ move -> moveCandidates << move
			}
		}
		return moveCandidates
	}
	
	Square getSquareNorthOf(Square originalSquare){
		if (!originalSquare) return null
		if (originalSquare.y<=1) return null
		else return squares.find{it.x==originalSquare.x && it.y==originalSquare.y-1}
	}
	
	Square getSquareSouthOf(Square originalSquare){
		if (!originalSquare) return null
		if (originalSquare.y>=boardHeight) return null
		else return squares.find{it.x==originalSquare.x && it.y==originalSquare.y+1}
	}
	
	Square getSquareWestOf(Square originalSquare){
		if (!originalSquare) return null
		if (originalSquare.x>=boardHeight) return null
		else return squares.find{it.x==originalSquare.x+1 && it.y==originalSquare.y}
	}
	
	Square getSquareEastOf(Square originalSquare){
		if (!originalSquare) return null
		if (originalSquare.x<=1) return null
		else return squares.find{it.x==originalSquare.x-1 && it.y==originalSquare.y}
	}
	
	Square getSquareNorthWestOf(Square originalSquare){
		getSquareNorthOf(getSquareWestOf(originalSquare))
	}
	
	Square getSquareNorthEastOf(Square originalSquare){
		getSquareNorthOf(getSquareEastOf(originalSquare))
	}
	
	Square getSquareSouthWestOf(Square originalSquare){
		getSquareSouthOf(getSquareWestOf(originalSquare))
	}
	
	Square getSquareSouthEastOf(Square originalSquare){
		getSquareSouthOf(getSquareEastOf(originalSquare))
	}
	
	Square getSquareByCoordinates(int x, int y){
		squares.find{it.x==x && it.y==y}
	}
	
	List<Square> getSquaresAround(Square baseSquare){
		List<Square> result = squares.findAll{Math.abs(it.x-baseSquare.x)<=1 && Math.abs(it.y-baseSquare.y)<=1}
		result.remove(baseSquare)
		result
	}
	
	int getDistance(Square square1, Square square2){
		return Math.max(Math.abs(square1.x-square2.x), Math.abs(square1.y-square2.y))
	}
	
	void putPieceOnSquare(Piece piece, int x, int y){
		Square square = squares.find{it.x==x && it.y==y}
		square.piece=piece
	}
}
