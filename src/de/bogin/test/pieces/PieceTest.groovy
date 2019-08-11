package de.bogin.test.pieces;

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.BoardRepository
import de.bogin.tenjiku.mcts.ElapsedTimer
import de.bogin.tenjiku.moves.Move
import de.bogin.tenjiku.pieces.Piece
import de.bogin.tenjiku.pieces.PieceRepository
import de.bogin.tenjiku.pieces.PieceType
import de.bogin.tenjiku.pieces.PlayerColour
import de.bogin.tenjiku.pieces.PieceRepository.EvaluationScheme


public class PieceTest {
    public static void main(String[] args) {
		BoardRepository boardRepository = new BoardRepository(EvaluationScheme.CHU_SHOGI)
		PieceRepository pieceRepository = boardRepository.pieceRepository
		Board board_55 = boardRepository.getEmptyBoard(5,5)
		
		Piece blackPhoenix = pieceRepository.create(PieceType.PHOENIX,PlayerColour.BLACK)
		
		board_55.putPieceOnSquare(blackPhoenix,3,3)
		
		PlayerColour colour = PlayerColour.BLACK
		ElapsedTimer t = new ElapsedTimer();
		
		List<Move> moves = board_55.generateMovesFor(colour)

        System.out.println(t);

        System.out.println("Done");
println moves
assert moves.size() == 8
    }
}