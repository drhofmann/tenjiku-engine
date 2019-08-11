package de.bogin.test;

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


public class TreeNodeTest_55Board {
    public static void main(String[] args) {
		BoardRepository boardRepository = new BoardRepository()
		PieceRepository pieceRepository = new PieceRepository()
		Board board_55 = boardRepository.getEmptyBoard(5,5)
		
		Piece blackpawn1 = pieceRepository.create(PieceType.PAWN,PlayerColour.BLACK)
		Piece blackpawn2 = pieceRepository.create(PieceType.PAWN,PlayerColour.BLACK)
		Piece blackpawn3 = pieceRepository.create(PieceType.PAWN,PlayerColour.BLACK)
		Piece blackdog = pieceRepository.create(PieceType.DOG,PlayerColour.BLACK)
		Piece whitepawn1 = pieceRepository.create(PieceType.PAWN,PlayerColour.WHITE)
		Piece whitepawn2 = pieceRepository.create(PieceType.PAWN,PlayerColour.WHITE)
		
		board_55.squares.find{it.x==2 && it.y==3}.piece=blackpawn1
		board_55.squares.find{it.x==1 && it.y==3}.piece=blackpawn2
		board_55.squares.find{it.x==3 && it.y==2}.piece=blackpawn3
		board_55.squares.find{it.x==2 && it.y==2}.piece=blackdog
		board_55.squares.find{it.x==3 && it.y==1}.piece=whitepawn1
		board_55.squares.find{it.x==5 && it.y==1}.piece=whitepawn2
		
		PlayerColour colour = PlayerColour.BLACK

		BoardPosition start = new BoardPosition(board_55, colour)
        TreeNode tn = new TreeNode(position: start);
		
        ElapsedTimer t = new ElapsedTimer();
        int n = 2;
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
			if (it.nVisits) println "Winrate for black after $it.position.lastMove : ${it.totValue/it.nVisits}"
		}
		
    }
}