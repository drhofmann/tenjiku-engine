package de.bogin.test

import de.bogin.tenjiku.board.Board;
import de.bogin.tenjiku.board.BoardRepository
import de.bogin.tenjiku.mcts.TreeNodeWithPositionEvaluation;
import de.bogin.tenjiku.pieces.PieceRepository;

abstract class BaseFindMoveScenario implements FindMoveScenario {

	BoardRepository boardRepository
	PieceRepository pieceRepository

	int numberOfSimulations
	int minutesToWait
	
	abstract Board setupBoard()
	abstract boolean checkSolution(def tn)
}
