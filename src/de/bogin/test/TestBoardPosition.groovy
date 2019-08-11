package de.bogin.test

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.BoardRepository
import de.bogin.tenjiku.moves.Move
import de.bogin.tenjiku.pieces.Piece
import de.bogin.tenjiku.pieces.PieceRepository
import de.bogin.tenjiku.pieces.PieceType
import de.bogin.tenjiku.pieces.PlayerColour
import de.bogin.tenjiku.position.BoardPosition

class TestBoardPosition {

	static main(args) {
		BoardRepository boardRepository = new BoardRepository()
		PieceRepository pieceRepository = new PieceRepository()
		Board board_33 = boardRepository.getEmptyBoard(3,3)
		
		Piece blackpawn1 = pieceRepository.create(PieceType.PAWN,PlayerColour.BLACK)
		Piece blackpawn2 = pieceRepository.create(PieceType.PAWN,PlayerColour.BLACK)
		Piece blackpawn3 = pieceRepository.create(PieceType.PAWN,PlayerColour.BLACK)
		Piece blackdog = pieceRepository.create(PieceType.DOG,PlayerColour.BLACK)
		Piece whitepawn1 = pieceRepository.create(PieceType.PAWN,PlayerColour.WHITE)
		
		board_33.squares.find{it.x==2 && it.y==3}.piece=blackpawn1
		board_33.squares.find{it.x==2 && it.y==2}.piece=blackdog
		board_33.squares.find{it.x==3 && it.y==2}.piece=blackpawn3
		board_33.squares.find{it.x==1 && it.y==3}.piece=blackpawn2
		board_33.squares.find{it.x==3 && it.y==1}.piece=whitepawn1
		PlayerColour colour = PlayerColour.BLACK

		BoardPosition start = new BoardPosition(board_33, colour)
		println "Moves for $colour from initial position"
		
		BoardPosition position = start
		for (int i:(1..7)){
			List<Move> moves = position.generateSensibleMovesForActivePlayer()
			if (!moves) {
				println "No more moves"
				break
			}
			moves.each{ println it }
			Move selectedMove = moves.first()
			BoardPosition position2 = position.getNewPositionAfterMove(selectedMove)
			println "Selected Move: $selectedMove"
			position = position.getNewPositionAfterMove(selectedMove)
			println "Moves for $position.playerToMove after the move # ${i+1}"
		}

	}
}
