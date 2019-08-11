package de.bogin.tenjiku.moves

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.Square
import de.bogin.tenjiku.pieces.Piece
import de.bogin.tenjiku.pieces.PlayerColour

class MovementRepository {

	List<Move> stepsInFront(int numberOfSteps, Board board, Square square){
		stepsInDirection(numberOfSteps, board, square, MovementDirection.FRONT)
	}

	List<Move> stepsBack(int numberOfSteps, Board board, Square square){
		stepsInDirection(numberOfSteps, board, square, MovementDirection.BACK)
	}

	List<Move> stepsLeft(int numberOfSteps, Board board, Square square){
		stepsInDirection(numberOfSteps, board, square, MovementDirection.LEFT)
	}

	List<Move> stepsRight(int numberOfSteps, Board board, Square square){
		stepsInDirection(numberOfSteps, board, square, MovementDirection.RIGHT)
	}

	List<Move> stepsFrontLeft(int numberOfSteps, Board board, Square square){
		stepsInDirection(numberOfSteps, board, square, MovementDirection.FRONT_LEFT)
	}

	List<Move> stepsFrontRight(int numberOfSteps, Board board, Square square){
		stepsInDirection(numberOfSteps, board, square, MovementDirection.FRONT_RIGHT)
	}

	List<Move> stepsBackLeft(int numberOfSteps, Board board, Square square){
		stepsInDirection(numberOfSteps, board, square, MovementDirection.BACK_LEFT)
	}

	List<Move> stepsBackRight(int numberOfSteps, Board board, Square square){
		stepsInDirection(numberOfSteps, board, square, MovementDirection.BACK_RIGHT)
	}

	List<Move> rangesInFront(Board board, Square square){
		rangesInDirection(board, square, MovementDirection.FRONT)
	}

	List<Move> rangesBack(Board board, Square square){
		rangesInDirection(board, square, MovementDirection.BACK)
	}

	List<Move> rangesLeft(Board board, Square square){
		rangesInDirection(board, square, MovementDirection.LEFT)
	}

	List<Move> rangesRight(Board board, Square square){
		rangesInDirection(board, square, MovementDirection.RIGHT)
	}

	List<Move> rangesFrontLeft(Board board, Square square){
		rangesInDirection(board, square, MovementDirection.FRONT_LEFT)
	}

	List<Move> rangesFrontRight(Board board, Square square){
		rangesInDirection(board, square, MovementDirection.FRONT_RIGHT)
	}

	List<Move> rangesBackLeft(Board board, Square square){
		rangesInDirection(board, square, MovementDirection.BACK_LEFT)
	}

	List<Move> rangesBackRight(Board board, Square square){
		rangesInDirection(board, square, MovementDirection.BACK_RIGHT)
	}

	List<Move> knightJumpsInFront(Board board, Square square){
		knightJumpsInDirection(board, square, MovementDirection.FRONT)
	}

	List<Move> knightJumpsBack(Board board, Square square){
		knightJumpsInDirection(board, square, MovementDirection.BACK)
	}

	List<Move> knightJumpsLeft(Board board, Square square){
		knightJumpsInDirection(board, square, MovementDirection.LEFT)
	}

	List<Move> knightJumpsRight(Board board, Square square){
		knightJumpsInDirection(board, square, MovementDirection.RIGHT)
	}
	
	List<Move> chessPawnMove(Board board, Square square){
		List<Move> result = []
		Square initialSquare = square
		Piece piece = square.piece
		PlayerColour currentPlayer = piece.owner
		PlayerColour opponent = currentPlayer.getOpposingColour()
		Square moveSquare = get1SquareInDirection(square, currentPlayer, board, MovementDirection.FRONT) 
		if (!moveSquare.piece){
			result << new Move(initialSquare: initialSquare, targetSquare: moveSquare, piece: piece)
		}
		List<Square> captureSquares = []
		List<MovementDirection> orthogonalDirections = MovementDirection.FRONT.getOrthogonalDirections()
		orthogonalDirections.each{
			captureSquares << get1SquareInDirection(moveSquare, currentPlayer, board, it)
		}
		captureSquares.each{targetSquare ->
			if (targetSquare?.piece?.owner==opponent){
				result << new Move(initialSquare: initialSquare, targetSquare: targetSquare, piece: piece, takesAPiece: true)
			}
		}		
		return result
	}


	private List<Move> stepsInDirection(int numberOfSteps, Board board, Square square, MovementDirection direction){
		List<Move> result = []
		Square initialSquare = square
		Piece piece = square.piece
		PlayerColour currentPlayer = piece.owner
		PlayerColour opponent = currentPlayer.getOpposingColour()
		Square targetSquare = initialSquare

		for ( i in 1..numberOfSteps) {
			targetSquare = get1SquareInDirection(targetSquare, currentPlayer, board, direction)

			if (!targetSquare) break

			else if (!targetSquare.piece){
				result << new Move(initialSquare: initialSquare, targetSquare: targetSquare, piece: piece)
				continue
			}

			else if (targetSquare.piece.owner==currentPlayer){
				break
			}
			else if (targetSquare.piece.owner==opponent){
				result << new Move(initialSquare: initialSquare, targetSquare: targetSquare, piece: piece, takesAPiece: true)
				break
			}
			else throw new RuntimeException("No valid action found for target square")
		}

		return result
	}

	private List<Move> rangesInDirection(Board board, Square square, MovementDirection direction){
		List<Move> result = new ArrayList<Move>()
		Square initialSquare = square
		Piece piece = square.piece
		PlayerColour currentPlayer = piece.owner
		PlayerColour opponent = currentPlayer.getOpposingColour()
		Square targetSquare = initialSquare

		while (targetSquare) {
			targetSquare = get1SquareInDirection(targetSquare, currentPlayer, board, direction)

			if (!targetSquare) break

			else if (!targetSquare.piece){
				result << new Move(initialSquare: initialSquare, targetSquare: targetSquare, piece: piece)
				continue
			}

			else if (targetSquare.piece.owner==currentPlayer){
				break
			}
			else if (targetSquare.piece.owner==opponent){
				result << new Move(initialSquare: initialSquare, targetSquare: targetSquare, piece: piece, takesAPiece: true)
				break
			}
			else throw new RuntimeException("No valid action found for target square")
		}

		return result
	}

	private List<Move> knightJumpsInDirection(Board board, Square square, MovementDirection direction){
		List<Move> result = []
		Square initialSquare = square
		Piece piece = square.piece
		PlayerColour currentPlayer = piece.owner
		PlayerColour opponent = currentPlayer.getOpposingColour()
		List<Square> targetSquares = getTargetSquaresForKnightJump(board, initialSquare, currentPlayer, direction)

		targetSquares.each{targetSquare ->
			if (!targetSquare.piece){
				result << new Move(initialSquare: initialSquare, targetSquare: targetSquare, piece: piece)
			} else if (targetSquare.piece.owner==currentPlayer){
			} else if (targetSquare.piece.owner==opponent){
				result << new Move(initialSquare: initialSquare, targetSquare: targetSquare, piece: piece, takesAPiece: true)
			}
			else throw new RuntimeException("No valid action found for target square")
		}
		return result
	}

	private Square get1SquareInDirection(Square initialSquare, PlayerColour colour, Board board, MovementDirection direction){
		BoardDirection boardDirection = getBoardDirectionFromMovementDirection(direction, colour)
		switch (boardDirection){
			case BoardDirection.NORTH:
				return board.getSquareNorthOf(initialSquare)
				break

			case BoardDirection.SOUTH:
				return board.getSquareSouthOf(initialSquare)
				break

			case BoardDirection.WEST:
				return board.getSquareWestOf(initialSquare)
				break

			case BoardDirection.EAST:
				return board.getSquareEastOf(initialSquare)
				break

			case BoardDirection.NORTH_WEST:
				return board.getSquareNorthWestOf(initialSquare)
				break

			case BoardDirection.NORTH_EAST:
				return board.getSquareNorthEastOf(initialSquare)
				break

			case BoardDirection.SOUTH_WEST:
				return board.getSquareSouthWestOf(initialSquare)
				break

			case BoardDirection.SOUTH_EAST:
				return board.getSquareSouthEastOf(initialSquare)
				break

			default:
				throw new RuntimeException("No valid direction for movement: $direction")
		}
		throw new RuntimeException("No valid piece colour: $colour")
	}

	private List<Square> getTargetSquaresForKnightJump(Board board, Square square, PlayerColour colour, MovementDirection direction){
		List<Square> result = []
		BoardDirection boardDirection = getBoardDirectionFromMovementDirection(direction, colour)
		Square twoSquaresInDirection = get1SquareInDirection(get1SquareInDirection(square, colour, board, direction), colour, board, direction)
		List<MovementDirection> orthogonalDirections = direction.getOrthogonalDirections()
		orthogonalDirections.each{
			Square targetSquare = get1SquareInDirection(twoSquaresInDirection, colour, board, it)
			if (targetSquare) {
			result << targetSquare
			}
		}
		return result
	}

	private BoardDirection getBoardDirectionFromMovementDirection(MovementDirection direction, PlayerColour colour){
		BoardDirection result
		if (colour==PlayerColour.BLACK){
			switch (direction){
				case MovementDirection.FRONT:
					result = BoardDirection.NORTH
					break

				case MovementDirection.BACK:
					result = BoardDirection.SOUTH
					break

				case MovementDirection.LEFT:
					result = BoardDirection.WEST
					break

				case MovementDirection.RIGHT:
					result = BoardDirection.EAST
					break

				case MovementDirection.FRONT_LEFT:
					result = BoardDirection.NORTH_WEST
					break

				case MovementDirection.FRONT_RIGHT:
					result = BoardDirection.NORTH_EAST
					break

				case MovementDirection.BACK_LEFT:
					result = BoardDirection.SOUTH_WEST
					break

				case MovementDirection.BACK_RIGHT:
					result = BoardDirection.SOUTH_EAST
					break

				default: throw new IllegalArgumentException ("No known movement direction $direction")
			}
		} else {
			switch (direction){
				case MovementDirection.FRONT:
					result = BoardDirection.SOUTH
					break

				case MovementDirection.BACK:
					result = BoardDirection.NORTH
					break

				case MovementDirection.LEFT:
					result = BoardDirection.EAST
					break

				case MovementDirection.RIGHT:
					result = BoardDirection.WEST
					break

				case MovementDirection.FRONT_LEFT:
					result = BoardDirection.SOUTH_EAST
					break

				case MovementDirection.FRONT_RIGHT:
					result = BoardDirection.SOUTH_WEST
					break

				case MovementDirection.BACK_LEFT:
					result = BoardDirection.NORTH_EAST
					break

				case MovementDirection.BACK_RIGHT:
					result = BoardDirection.NORTH_WEST
					break

				default: throw new IllegalArgumentException ("No known movement direction $direction")
			}
		}
		result
	}
}
