package de.bogin.tenjiku.board

import de.bogin.tenjiku.moves.Move
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
}
