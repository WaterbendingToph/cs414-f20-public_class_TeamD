package cs414f20.teamd.Gameplay;

import java.util.ArrayList;

abstract class ChessPiece {
    /**
     * This is the parent class for all the concrete chess piece classes. This class
     * keeps a reference to the board that the piece is on (if any), the position
     * where the piece is located, and its color.
     */

    public enum Color {
        WHITE, BLACK
    }
    protected ChessBoard board;
    protected int row;
    protected int column;
    protected Color color;

    protected ChessPiece() {
        // No-arg constructor for concrete classes defaults to white
        this(new ChessBoard(), Color.WHITE);
    }

    protected ChessPiece(ChessBoard board, Color color) {
        this.board = board;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public String getPosition() {
        /**
         * Returns the column/row of the current piece in standard chess nomenclature.
         * Namely, returns a two-character string representing the column as a character
         * from a-h, and the row as a number from 1-8 (e.g. "e5")
         * @return A two-character string representing the piece's current position
         * @see ChessPiece
         */

        // Convert the row and column indices to characters and return them with
        // column first
        char charRow = (char)(this.row + 48);
        char charColumn = (char)(this.column + 97);
        String currentPosition = "" + charRow + charColumn;

        return currentPosition;
    }

    public void setPosition(String position) throws IllegalPositionException {
        /**
         * Sets the position of the current piece to the requested position, if
         * the request is reachable with a legal move. If the requested position 
         * is not reachable with a legal move, this method throws an IllegalPositionException.
         * @param position The two-character position where the piece will be moved
         * @throws IllegalPositionException
         */
        // Ensure that the move is legal (i.e. the position is on the board)
        if (position.charAt(0) >= 'a' && position.charAt(0) <= 'h' && position.charAt(1) >= '1'
        		&& position.charAt(1) <= '8') {
        	
	        this.row = (int)(position.charAt(1) - 49);
	        this.column = (int)(position.charAt(0) - 97);
        } else {
            throw new IllegalPositionException("Not a legal move.");
        }
    }

    @Override
    public abstract String toString();

    public abstract ArrayList<String> legalMoves();

}
