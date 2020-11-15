package cs414f20.teamd.Gameplay;

import java.util.ArrayList;

public class Wizard extends ChessPiece{

    public Wizard(ChessBoard board, Color color) {
        super(board, color);
    }

    /**
     * This concrete class implements the methods for the abstract class ChessPiece
     * for the wizard piece in this variant of chess. The wizard can hop in a 3x1 pattern,
     * or move one space diagonally in any direction (1x1).
     */

    @Override
    public String toString() {
        if (color == ChessPiece.Color.WHITE)
            return "\u263D";
        else
            return "\u263C";
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
