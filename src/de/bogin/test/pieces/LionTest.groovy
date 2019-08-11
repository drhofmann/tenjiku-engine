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


public class LionTest {
    public static void main(String[] args) {
		BoardRepository boardRepository = new BoardRepository(EvaluationScheme.CHU_SHOGI)
		PieceRepository pieceRepository = boardRepository.pieceRepository
		Board board_55 = boardRepository.getEmptyBoard(5,5)
		
		Piece blackLion = pieceRepository.create(PieceType.LION_TENJIKU,PlayerColour.BLACK)
		Piece whitePawn = pieceRepository.create(PieceType.PAWN,PlayerColour.WHITE)
		Piece whiteRook = pieceRepository.create(PieceType.ROOK,PlayerColour.WHITE)
		Piece whiteLion = pieceRepository.create(PieceType.LION_TENJIKU,PlayerColour.WHITE)
		
		board_55.putPieceOnSquare(blackLion,3,3)
		board_55.putPieceOnSquare(whitePawn,2,4)
		board_55.putPieceOnSquare(whiteRook,2,2)
		board_55.putPieceOnSquare(whiteLion,5,1)
		board_55.putPieceOnSquare(whiteLion,5,5)
		board_55.putPieceOnSquare(whiteRook,4,5)
		board_55.putPieceOnSquare(whiteLion,1,1)
		board_55.putPieceOnSquare(whiteLion,1,5)
		
		
		PlayerColour colour = PlayerColour.BLACK
		ElapsedTimer t = new ElapsedTimer();
		
		List<Move> moves = board_55.generateMovesFor(colour)

        System.out.println(t);

        System.out.println("Done");
		println moves
assert moves.size() == 40
    }
}