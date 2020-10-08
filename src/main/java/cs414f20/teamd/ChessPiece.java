package cs414f20.teamd;

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

    public ChessPiece() {
        // No-arg constructor for concrete classes defaults to white
        this(new ChessBoard(), Color.WHITE);
    }

    public ChessPiece(ChessBoard board, Color color) {
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

        return "Not yet implemented";
    }

    public void setPosition(String position) throws IllegalPositionException {
        /**
         * Sets the position of the current piece to the requested position, if
         * the request is reachable with a legal move. If the requested position 
         * is not reachable with a legal move, this method throws an IllegalPositionException.
         * @param position The two-character position where the piece will be moved
         * @throws IllegalPositionException
         */
        System.out.printf("The requested position is: %s.", position);
        System.out.println("Not yet implemented");
    }

    @Override
    public abstract String toString();

    public abstract ArrayList<String> legalMoves();

}
