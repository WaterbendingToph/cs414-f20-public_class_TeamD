package cs414f20.teamd.Gameplay;

import java.util.ArrayList;

class Rook extends ChessPiece {
    /**
     * This concrete class implements the methods for the abstract class ChessPiece
     * for the rook piece in this variant of chess. A rook can move any number of
     * squares horizontally or vertically, forward or backward, as long as it does
     * not have to leap over other pieces. At the end of the move, it can occupy
     * a previously empty square or capture (replace) an opponent's piece, but it
     * cannot replace another piece of the same player.
     */

    // No-arg constructor for concrete classes defaults to white
    public Rook() {
        super();
    }
    
    // One-arg constructor for concrete class instantiation defaults to white
    public Rook(ChessBoard board) {
        super(board, Color.WHITE);
    }

    // Constructor with both arguments given
    public Rook(ChessBoard board, Color color) {
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

        return this.color == Color.WHITE ? "\u2656" : "\u265C";
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
        
        ArrayList<String> legalMoves = new ArrayList<>();

        // TODO: Write code to iterate over potential moves for king piece.
        legalMoves.add("Not yet implemented.");
        
        return legalMoves;
    }
}
