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

	BoardPosition(Board board, PlayerColour colour){
		this.board=board
		this.playerToMove=colour
	}

	BoardPosition getNewPositionAfterMove(Move move){
		assert move.piece.owner==playerToMove, "Wrong player to move!"
		BoardPosition result = new BoardPosition(boardRepository.getCopyOf(board), playerToMove.getOpposingColour())
		result.board.getSquareByCoordinates(move.initialSquare.x, move.initialSquare.y).piece=null
		result.board.getSquareByCoordinates(move.targetSquare.x, move.targetSquare.y).piece=move.piece
		result.lastMove=move
		return result
	}

	List<Move> generateSensibleMovesForActivePlayer(){
		List<Move> moveCandidates = board.generateMovesFor(playerToMove)
		
		List<Move> movesThatRunIntoCheck = new ArrayList<Move>()
		moveCandidates.each{
			BoardPosition newPosition = getNewPositionAfterMove(it)
			List<Square> squaresAttackedByOpponentAfterMove = newPosition.getSquaresAttackedBy(playerToMove.getOpposingColour())
			Square squareWithOwnKingAfterMove = newPosition.getSquareWithKingBy(playerToMove)
			if (squaresAttackedByOpponentAfterMove.contains(squareWithOwnKingAfterMove)){
				movesThatRunIntoCheck <<it
			}	
		}
		movesThatRunIntoCheck.each{moveCandidates.remove(it) }
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
		double valueOfBlackPieces = board.getSquaresWithPiecesOf(PlayerColour.BLACK).collect{it.piece.type.value}.sum()
		double valueOfWhitePieces = board.getSquaresWithPiecesOf(PlayerColour.WHITE).collect{it.piece.type.value}.sum()
		return valueOfBlackPieces - valueOfWhitePieces
	}
}