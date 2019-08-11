package de.bogin.test

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.BoardRepository;
import de.bogin.tenjiku.mcts.TreeNodeWithPositionEvaluation
import de.bogin.tenjiku.pieces.PieceRepository;

interface FindMoveScenario {
	
	int numberOfSimulations
	int minutesToWait
	
	Board setupBoard()
	boolean checkSolution(def tn)
}
