package de.bogin.test.mcts;

import de.bogin.tenjiku.board.Board
import de.bogin.tenjiku.board.BoardRepository
import de.bogin.tenjiku.mcts.ElapsedTimer
import de.bogin.tenjiku.mcts.TreeNode
import de.bogin.tenjiku.mcts.TreeView
import de.bogin.tenjiku.pieces.Piece
import de.bogin.tenjiku.pieces.PieceRepository
import de.bogin.tenjiku.pieces.PieceType
import de.bogin.tenjiku.pieces.PlayerColour
import de.bogin.tenjiku.position.BoardPosition


public class RookKingVsKing {
    /**
     * @param args
     */
    public static void main(String[] args) {
		BoardRepository boardRepository = new BoardRepository()
		PieceRepository pieceRepository = new PieceRepository()
		Board chessBoard = boardRepository.getEmptyBoard(4,4)
		
		Piece blackKing = pieceRepository.create(PieceType.KING,PlayerColour.BLACK)
		Piece blackRook = pieceRepository.create(PieceType.ROOK,PlayerColour.BLACK)
		Piece whiteKing = pieceRepository.create(PieceType.KING,PlayerColour.WHITE)
		
		chessBoard.squares.find{it.x==3 && it.y==4}.piece=blackKing
		chessBoard.squares.find{it.x==1 && it.y==3}.piece=blackRook
		chessBoard.squares.find{it.x==4 && it.y==1}.piece=whiteKing

		PlayerColour colour = PlayerColour.BLACK

		BoardPosition start = new BoardPosition(chessBoard, colour)
        TreeNode tn = new TreeNode(position: start);
		
		println start.generateSensibleMovesForActivePlayer()
        ElapsedTimer t = new ElapsedTimer();
        int n = 100;
        for (int i=0; i<n; i++) {
            tn.selectAction();
        }
        System.out.println(t);
        //TreeView tv = new TreeView(tn);
        //tv.showTree("After " + n + " play outs");
        System.out.println("Done");
		println tn.totValue
		println tn.nVisits
		println "Winrate for black: ${tn.totValue/tn.nVisits}"
		tn.children.each{
			if (it.nVisits>0)
			println "Winrate for black after $it.position.lastMove : ${it.totValue/it.nVisits} - $it.totValue / $it.nVisits"
		}
		tn.flatten()
		println tn.mostLikelySequence
		println tn.blackWinrateForMostLikelySequence
		
    }
}