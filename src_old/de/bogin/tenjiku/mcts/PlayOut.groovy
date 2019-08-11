package de.bogin.tenjiku.mcts

import java.util.Random;

import de.bogin.tenjiku.board.Square
import de.bogin.tenjiku.moves.Move
import de.bogin.tenjiku.pieces.PieceType;
import de.bogin.tenjiku.pieces.PlayerColour;
import de.bogin.tenjiku.position.BoardPosition

class PlayOut {
	BoardPosition initialPosition
	double result
	static Random r = new Random();

	double run(){
		List<Move> moves = initialPosition.generateSensibleMovesForActivePlayer()
		if (!moves) {
			if (initialPosition.playerToMove == PlayerColour.BLACK) {
				result = 0
			} else {
				result = 1
			}
			return result
		}
		BoardPosition position = initialPosition
		int playedMovesFromInitialPosition = 0
		while (moves){
			playedMovesFromInitialPosition++
			Move selectedMove = selectMove(moves, position)
			if (!selectedMove&&moves){
				if (position.playerToMove==PlayerColour.BLACK)		{
					result = 0
					break
				} else {
					result = 1
					break
				}
			} else if (!moves){
				result = 0.5
				break
			}

			if (selectedMove.targetSquare.piece?.type==PieceType.KING){
				if (selectedMove.targetSquare.piece.owner==PlayerColour.BLACK){
					result = 0
					break
				} else {
					result = 1
					break
				}
			}
			position = position.getNewPositionAfterMove(selectedMove)
			moves = position.generateSensibleMovesForActivePlayer()
			if (playedMovesFromInitialPosition>100){
				result = 0.5
				break
			}
		}
		return result
	}

	Move selectMove(List<Move> moves, BoardPosition position){
		List<Square> squaresAttackedByOpponent = position.getSquaresAttackedBy(position.playerToMove.getOpposingColour())
		Square squareWithOwnKing = position.getSquareWithKingBy(position.playerToMove)
		if (squaresAttackedByOpponent.contains(squareWithOwnKing)){
			List<Move> movesThatRemainInCheck = new ArrayList<Move>()
			moves.each{
				BoardPosition newPosition = position.getNewPositionAfterMove(it)
				if (newPosition.getSquaresAttackedBy(position.playerToMove.getOpposingColour()).contains(newPosition.getSquareWithKingBy(position.playerToMove))){
					movesThatRemainInCheck <<it
				}
			}
			movesThatRemainInCheck.each{moves.remove(it) }
		}
		if (moves){
			int moveNumber = r.nextInt(moves.size())
			return moves[moveNumber]
		} else {
			return null
		}
	}
}