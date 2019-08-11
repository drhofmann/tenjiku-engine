package de.bogin.test

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.BoardRepository
import de.bogin.tenjiku.board.Square
import de.bogin.tenjiku.moves.Move
import de.bogin.tenjiku.pieces.Piece
import de.bogin.tenjiku.pieces.PieceRepository
import de.bogin.tenjiku.pieces.PieceType;
import de.bogin.tenjiku.pieces.PlayerColour

class TestBoard {

	static main(args) {
		BoardRepository boardRepository = new BoardRepository()
		PieceRepository pieceRepository = new PieceRepository()
		Board board_33 = boardRepository.getEmptyBoard(3,3)
		
		Piece blackpawn1 = pieceRepository.create(PieceType.PAWN,PlayerColour.BLACK)
		Piece blackpawn2 = pieceRepository.create(PieceType.PAWN,PlayerColour.BLACK)
		Piece blackpawn3 = pieceRepository.create(PieceType.PAWN,PlayerColour.BLACK)
		Piece blackpawn4 = pieceRepository.create(PieceType.PAWN,PlayerColour.BLACK)
		Piece whitepawn1 = pieceRepository.create(PieceType.PAWN,PlayerColour.WHITE)
		
		board_33.squares.find{it.x==2 && it.y==3}.piece=blackpawn1
		board_33.squares.find{it.x==2 && it.y==2}.piece=blackpawn2
		board_33.squares.find{it.x==3 && it.y==2}.piece=blackpawn3
		board_33.squares.find{it.x==1 && it.y==3}.piece=blackpawn4
		board_33.squares.find{it.x==3 && it.y==1}.piece=whitepawn1

		//board.setup()
		PlayerColour colour = PlayerColour.BLACK
		
		List<Square> bp = board_33.getSquaresWithPiecesOf(colour)
		println bp
		println board_33.getSquaresWithPiecesOf(colour.getOpposingColour())
		println "These are the moves for $colour:"
		List<Move> moves = board_33.generateMovesFor(colour)
		moves.each{
			println it		}
		}
}
