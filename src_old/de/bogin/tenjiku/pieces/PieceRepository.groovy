package de.bogin.tenjiku.pieces

import de.bogin.tenjiku.moves.MovementRepository

class PieceRepository {
	MovementRepository movementRepository = new MovementRepository()
	Piece create (PieceType pieceType, PlayerColour colour){
		Piece piece
		switch(pieceType)
		{
			case PieceType.PAWN:
			piece = new Pawn()
			break
			
			case PieceType.PAWN_CHESS:
			piece = new Pawn_Chess()
			break
			
			case PieceType.DOG:
			piece = new Dog()
			break
			
			case PieceType.KING:
			piece = new King()
			break
			
			case PieceType.ROOK:
			piece = new Rook()
			break
			
			case PieceType.BISHOP:
			piece = new Bishop()
			break
			
			case PieceType.FREEKING:
			piece = new FreeKing()
			break
			
			case PieceType.KNIGHT_CHESS:
			piece = new Knight_Chess()
			break
			
			case PieceType.KNIGHT:
			piece = new Knight()
			break
			
			default:
			throw new RuntimeException("Unknown piece type $pieceType")
		}
		piece.owner = colour
		piece.movementRepository=movementRepository
		return piece
	}
}
