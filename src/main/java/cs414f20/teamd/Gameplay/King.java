package cs414f20.teamd.Gameplay;

import java.util.ArrayList;

public class King extends ChessPiece {
    /**
     * This concrete class implements the methods for the abstract class ChessPiece
     * for the king piece in this variant of chess. The king can only move one
     * square in any direction. At the end of the move, it can occupy a previously 
     * empty square or capture (replace) an opponent's piece, but it cannot replace 
     * another piece of the same player.
     */

    // No-arg constructor for concrete classes defaults to white
    public King() {
        super();
    }

    // One-arg constructor for concrete class instantiation defaults to white
    public King(ChessBoard board) {
        super(board, Color.WHITE);
    }

    // Constructor with both arguments given
    public King(ChessBoard board, Color color) {
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

        return this.color == Color.WHITE ? "\u2654" : "\u265A";
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

        // Identify potential rows and columns that the king could move to. This
        // is simply a representation of the way the kind moves, not necessarily
        // if those positions are available
        int[] potentialRows = { this.row - 1, this.row, this.row + 1 };
        int[] potentialColumns = { this.column - 1, this.column, this.column + 1 };

        // Check each potential row/column combination to see if it is on the board
        // and not currently occupied by a piece of the same color
        for (int r : potentialRows) {
            for (int c : potentialColumns) {
                //  Ignore the current position
                if (r == this.row && c == this.column)
                    continue;
                // Ignore positions that aren't on the board
                if (r < 0 || r > 7 || c < 0 || c > 7) {
                    continue;
                }
                char potentialRow = (char) (r + 1);
                char potentialColumn = (char) (c + 49);
                String potentialPosition = "" + potentialColumn + potentialRow;

                legalMoves.add(potentialPosition);
            }
        }
        
        return legalMoves;
    }
}
