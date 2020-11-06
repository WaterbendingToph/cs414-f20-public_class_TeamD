package cs414f20.teamd.Gameplay;

import java.util.ArrayList;

public class Pawn extends ChessPiece {
    /**
     * This concrete class implements the methods for the abstract class ChessPiece
     * for the pawn piece in this variant of chess. A pawn in the initial position
     * may move one or two squares vertically forward to an empty square but cannot
     * leap over any piece. Subsequently, it can move only one square vertically
     * forward to an empty square. A pawn may capture (replace) an opponent's piece
     * diagonally one square in front of it. Pawns can never move backwards. En 
     * passant and promotion are not implemented in this variant of chess.
     */

    // No-arg constructor for concrete classes defaults to white
    public Pawn() {
        super();
    }

    // One-arg constructor for concrete class instantiation defaults to white
    public Pawn(ChessBoard board) {
        super(board, Color.WHITE);
    }

    // Constructor with both arguments given
    public Pawn(ChessBoard board, Color color) {
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

        return this.color == Color.WHITE ? "\u2659" : "\u265F";
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
        
        ArrayList<String> moves = new ArrayList<>();

        String pawnPosition = this.getPosition();
        // Legal white moves
        if (this.color == Color.WHITE) {
        	// Account for first move being 2 spaces instead of 1
        	if (this.row == 1) {
        		// Add 3 instead of two because index is one lower than row
	        	char pawnRow = (char)(pawnPosition.charAt(0) + 3);
	        	char pawnCol = pawnPosition.charAt(1);
	        	String pawnFirstMove = "" + pawnCol + pawnRow;
	        	moves.add(pawnFirstMove);
        	}
        	// Regular forward moves
        	char pawnRow = (char)(pawnPosition.charAt(0) + 2);
        	char pawnCol = pawnPosition.charAt(1);
        	String pawnFirstMove = "" + pawnCol + pawnRow;
        	moves.add(pawnFirstMove);
        }
        
        // Legal black moves
        if (this.color == Color.BLACK) {
        	if (this.row == 6) {
        		// Subtract 1 instead of 2 because index is one lower than row
        		char pawnRow = (char)(pawnPosition.charAt(0) - 1);
	        	char pawnCol = pawnPosition.charAt(1);
	        	String pawnFirstMove = "" + pawnCol + pawnRow;
	        	moves.add(pawnFirstMove);
        	}
        	char pawnRow = (char)(pawnPosition.charAt(0));
        	char pawnCol = pawnPosition.charAt(1);
        	String pawnFirstMove = "" + pawnCol + pawnRow;
        	moves.add(pawnFirstMove);
        }
        
        return moves;
    }
}
