package cs414f20.teamd.Gameplay;

import java.util.ArrayList;

class Queen extends ChessPiece {
    /**
     * This concrete class implements the methods for the abstract class ChessPiece
     * for the queen piece in this variant of chess. For simplicity, a queen may
     * not move at all in this variant, and thus has no legal moves at any point.
     */

    // No-arg constructor for concrete classes defaults to white
    public Queen() {
        super();
    }

    // One-arg constructor for concrete class instantiation defaults to white
    public Queen(ChessBoard board) {
        super(board, Color.WHITE);
    }

    // Constructor with both arguments given
    public Queen(ChessBoard board, Color color) {
        super(board, color);
    }

    public String toString() {
        /**
         * Returns a one-character string corresponding to the Unicode representation
         * of the piece. These Unicode values are given in the A2.pdf description.
         * Note that the character returned must correspond to the correct color.
         * 
         * @return One-character Unicode representation of the piece (black or white)
         */

        return this.color == Color.WHITE ? "\u2655" : "\u265B";
    }

    public ArrayList<String> legalMoves() {
        /**
         * Returns all legal moves that this piece can make based on the rules of 
         * this variant of chess (as described in the comment at the top of this
         * class). Each string in the list should represent a legal destination
         * for the piece. The order of the moves in the list is arbitrary. If there
         * are no legal moves, returns an empty ArrayList.
         * NOTE: For thie simple variant in assignment A2, queens may not move.
         *       Therefore, this method will only ever return an empty ArrayList.
         *
         * @return An empty ArrayList (since a queen may not move)
         */

        return new ArrayList<>();
    } 
}