package de.bogin.test.mcts;

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.BoardRepository
import de.bogin.tenjiku.mcts.ElapsedTimer
import de.bogin.tenjiku.mcts.TreeNodeWithPositionEvaluation
import de.bogin.tenjiku.mcts.TreeNodeWithPositionEvaluationWithoutBoards;
import de.bogin.tenjiku.pieces.PieceRepository
import de.bogin.tenjiku.pieces.PlayerColour
import de.bogin.tenjiku.pieces.PieceRepository.EvaluationScheme
import de.bogin.tenjiku.position.BoardPosition
import de.bogin.test.FindMoveScenario
import de.bogin.test.KaufmanTestSuite.KaufmanProblem01WithPositionEvaluation


public abstract class BaseTestFindMoveWithoutBoards {

	static PieceRepository pieceRepository
	static BoardRepository boardRepository

	public static void main(String[] args) {
		boardRepository = new BoardRepository(EvaluationScheme.CHESS)
		pieceRepository = boardRepository.pieceRepository

		FindMoveScenario scenario = new ChessMateIn4WithPositionEvaluation(boardRepository, pieceRepository)
		//FindMoveScenario scenario = new ChessMateIn3WithPositionEvaluation(boardRepository, pieceRepository)
		//FindMoveScenario scenario = new KaufmanProblem01WithPositionEvaluation(boardRepository, pieceRepository)
	
		Board board = scenario.setupBoard()

		PlayerColour colour = PlayerColour.BLACK

		BoardPosition start = new BoardPosition(board, colour, boardRepository)
		TreeNodeWithPositionEvaluationWithoutBoards tn = new TreeNodeWithPositionEvaluationWithoutBoards(playerToMove: PlayerColour.BLACK);

		println start.evaluate()
		int numberOfSimulations, minutesToWait
		numberOfSimulations = scenario.numberOfSimulations
		minutesToWait = scenario.minutesToWait

		println start.generateSensibleMovesForActivePlayer()
		ElapsedTimer t = new ElapsedTimer();
		int i = 0
		while(i<numberOfSimulations){
			tn.selectAction(start);
			if (t.elapsed()>minutesToWait*60000){
				break
			}
			i++
			if(i % 1000==0){
				tn.flatten()
				println "Most likely sequence after $i simulations (${t.elapsed() / 1000} sec): $tn.mostLikelySequence - score: $tn.scoreForMostLikelySequence"
			}
		}

		System.out.println(t);
		System.out.println("Done");
		println tn.totValue
		println tn.nVisits
		println "Average score (for black): ${tn.totValue/tn.nVisits}"
		tn.children.each{
			if (it.nVisits>0)
				println "Score after $it.lastMove : ${it.totValue/it.nVisits} - $it.totValue / $it.nVisits"
		}
		tn.flatten()
		println tn.mostLikelySequence
		println tn.scoreForMostLikelySequence


		println "UCT values for direct daughter nodes:"
		tn.children.each{
			if (it.nVisits&&it.totValue) {
				double uctValue = tn.calculateUctValue(it)
				println "UCT value for move $it.lastMove : $uctValue"
			}
		}
		assert scenario.checkSolution(tn)
	}
}