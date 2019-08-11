package de.bogin.tenjiku.position

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.BoardRepository
import de.bogin.tenjiku.board.Square
import de.bogin.tenjiku.moves.Move
import de.bogin.tenjiku.pieces.PieceType
import de.bogin.tenjiku.pieces.PlayerColour

class BoardPosition {
	BoardRepository boardRepository = new BoardRepository()
	Board board
	PlayerColour playerToMove
	Move lastMove
	double value = 0

	BoardPosition(Board board, PlayerColour colour, boardRepository){
		this.board = board
		this.playerToMove = colour
		this.boardRepository = boardRepository
	}

	BoardPosition getNewPositionAfterMove(Move move){
		assert move.piece.owner==playerToMove, "Wrong player to move!"
		BoardPosition result = new BoardPosition(boardRepository.getCopyOf(board), playerToMove.getOpposingColour(), boardRepository)
		result.board.getSquareByCoordinates(move.initialSquare.x, move.initialSquare.y).piece=null
		result.board.getSquareByCoordinates(move.targetSquare.x, move.targetSquare.y).piece=move.piece
		result.lastMove=move
		return result
	}
	
	def makeMove(Move move){
		assert move.piece.owner==playerToMove, "Wrong player to move!"
		board.getSquareByCoordinates(move.initialSquare.x, move.initialSquare.y).piece=null
		board.getSquareByCoordinates(move.targetSquare.x, move.targetSquare.y).piece=move.piece
		lastMove=move
		playerToMove = playerToMove.opposingColour
	}

	List<Move> generateSensibleMovesForActivePlayer(){
		List<Move> moveCandidates = board.generateMovesFor(playerToMove)
		
		List<Move> movesThatRunIntoCheck = new ArrayList<Move>()
		moveCandidates.each{
			BoardPosition newPosition = getNewPositionAfterMove(it)
			List<Square> squaresAttackedByOpponentAfterMove = newPosition.getSquaresAttackedBy(playerToMove.getOpposingColour())
			Square squareWithOwnKingAfterMove = newPosition.getSquareWithKingBy(playerToMove)
			if (squaresAttackedByOpponentAfterMove.findAll{it==squareWithOwnKingAfterMove}){
				movesThatRunIntoCheck << it
			}
		}
		movesThatRunIntoCheck.each{
			moveCandidates.remove(it)
			 }
		return moveCandidates
	}

	List<Square> getSquaresAttackedBy(PlayerColour colour){
		List<Move> possibleMoves = board.generateMovesFor(colour)
		return possibleMoves.collect{it.targetSquare}
	}

	Square getSquareWithKingBy(PlayerColour colour){
		return board.squares.find{it.piece?.owner==colour&&it.piece?.type==PieceType.KING}
	}
	
	double evaluate(){
		if (value == 0) {
			double valueOfBlackPieces = board.getSquaresWithPiecesOf(PlayerColour.BLACK).collect{it.piece.value}.sum()
			double valueOfWhitePieces = board.getSquaresWithPiecesOf(PlayerColour.WHITE).collect{it.piece.value}.sum()
			value = valueOfBlackPieces - valueOfWhitePieces
		}
		return value
	}
}