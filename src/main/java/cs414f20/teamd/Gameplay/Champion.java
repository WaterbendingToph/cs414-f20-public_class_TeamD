package cs414f20.teamd.Gameplay;

import java.util.ArrayList;

public class Champion extends ChessPiece {

    public Champion(ChessBoard board, Color color) {
        super(board, color);
    }

    /**
     * This concrete class implements the methods for the abstract class ChessPiece
     * for the champion piece in this variant of chess. The champion can move 1-2
     * spaces in the cardinal directions, or exactly two spaces diagonally.
     */

    public String toString() {
        if (color == ChessPiece.Color.WHITE)
            return "\u2616";
        else
            return "\u2617";
    }

    public ArrayList<String> legalMoves() {
        /**
         * Returns all legal moves that this piece can make based on the rules of 
         * this variant of chess (as described in the comment at the top of this
         * class). Each string in the list should represent a legal destination
         * for the piece. The order of the moves in the list is arbitrary. If there
         * are no legal moves, returns an empty ArrayList.
         * 
         * @return ArrayList representing the legal moves of the piece from the
         *         current position
         * @return An empty ArrayList if there are no legal moves available
         */
        
        System.out.println("Not yet implemented");
        return new ArrayList<>();
    }
}
