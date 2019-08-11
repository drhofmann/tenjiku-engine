package de.bogin.test.pieces;

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.BoardRepository
import de.bogin.tenjiku.mcts.ElapsedTimer
import de.bogin.tenjiku.moves.Move
import de.bogin.tenjiku.pieces.Piece
import de.bogin.tenjiku.pieces.PieceRepository
import de.bogin.tenjiku.pieces.PieceType
import de.bogin.tenjiku.pieces.PlayerColour


public class Knight_ChessTest {
    public static void main(String[] args) {
		BoardRepository boardRepository = new BoardRepository()
		PieceRepository pieceRepository = new PieceRepository()
		Board board_55 = boardRepository.getEmptyBoard(5,5)
		
		Piece blackKnight = pieceRepository.create(PieceType.KNIGHT_CHESS, PlayerColour.BLACK)
		
		board_55.squares.find{it.x==3 && it.y==3}.piece=blackKnight
		
		PlayerColour colour = PlayerColour.BLACK
		ElapsedTimer t = new ElapsedTimer();
		
		List<Move> moves = board_55.generateMovesFor(PlayerColour.BLACK)

        System.out.println(t);

        System.out.println("Done");
println moves
assert moves.size() == 8
    }
}