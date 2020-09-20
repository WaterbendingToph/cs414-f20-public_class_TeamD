package cs414f20.teamd;

import java.util.ArrayList;

public class Pawn {
    /**
     * This concrete class implements the methods for the abstract class ChessPiece
     * for the pawn piece in this variant of chess. A pawn in the initial position
     * may move one or two squares vertically forward to an empty square but cannot
     * leap over any piece. Subsequently, it can move only one square vertically
     * forward to an empty square. A pawn may capture (replace) an opponent's piece
     * diagonally one square in front of it. Pawns can never move backwards. En 
     * passant and promotion are not implemented in this variant of chess.
     */

    public String toString() {
        /**
         * Returns a one-character string corresponding to the Unicode representation
         * of the piece. These Unicode values are given in the A2.pdf description.
         * Note that the character returned must correspond to the correct color.
         * 
         * @return One-character Unicode representation of the piece (black or white)
         */

        return "Not yet implemented.";
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
