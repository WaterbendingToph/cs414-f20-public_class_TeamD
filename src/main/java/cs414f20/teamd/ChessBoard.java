package cs414f20.teamd;

import java.io.*;

import cs414f20.teamd.Gameplay.IllegalMoveException;
import cs414f20.teamd.Gameplay.IllegalPositionException;

public class ChessBoard {
    /**
     * This class stores the state of a board and its pieces.
     */

    private ChessPiece[][] board;

    public ChessBoard() {
        /**
         * Initializes an 8x8 chess board with no pieces on it.
         */
    }

    public void initialize() {
        /**
         * Initializes the board to the standard chess opening state with indexing
         * based on standard chess notation (e.g. "e5"). Uses the placePiece method
         * to correctly position each piece.
         * 
         * @see placePiece
         */
    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {
        /**
         * Returns the chess piece at the given position on the board. If the given
         * position is illegal because the string contains illegal characters or 
         * represents a position outside the board, throws an IllegalPositionException.
         * 
         * @param position  The two-character representation of the position, where
         *                  the first character is a lowercase a-h representing the column and the
         *                  second character is a number 1-8 representing the row.
         * @return          a ChessPiece object representing the type of piece at the position
         * @throws          IllegalPositionException
         * @see             ChessPiece
         */
        return null;
    }

    public boolean placePiece(ChessPiece piece, String position) {
        /**
         * Tries to place the given piece at the given position. Returns true if
         * the piece was successfully placed or false if the position is already
         * occupied by the same player, or if the move was illegal.
         * 
         * @param  piece    ChessPiece requested to be moved
         * @param  position The two-character identifier of the requested position
         * @return True     If the move is legal and able to be completed
         * @return False    If the position is already occupied by the current player
         * @return False    If the move is illegal because the requested position
         *                  is not on the board or contains illegal characters.
         */

        return false;
    }

    public void move(String fromPosition, String toPosition) throws IllegalMoveException {
        /**
         * Checks if moving the piece from fromPosition to toPosition is a legal
         * move. Legality is defined by the rules of this particular variant of 
         * chess. If the move is legal, it executes the move, changing the value
         * of the board as needed. Otherwise, an IllegalMoveException is thrown.
         * 
         * @param  fromPosition a two-character identifier of the original position
         * @param  toPosition   a two-character identifier of the new position requested
         * @throws IllegalMoveException 
         */

        System.out.println("Not yet implemented");
    }

    @Override
    public String toString() {
        /*
         * ----------------------------DO NOT EDIT----------------------------
         * Displays the chess board in a user-friendly format. This method was
         * provided by the instructor via Canvas and should not be modified.
         */

        String chess="";
        String upperLeft = "\u250C";
        String upperRight = "\u2510";
        String horizontalLine = "\u2500";
        String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
        String verticalLine = "\u2502";
        String upperT = "\u252C";
        String bottomLeft = "\u2514";
        String bottomRight = "\u2518";
        String bottomT = "\u2534";
        String plus = "\u253C";
        String leftT = "\u251C";
        String rightT = "\u2524";

        String topLine = upperLeft;
        for (int i = 0; i<7; i++){
            topLine += horizontal3 + upperT;
        }
        topLine += horizontal3 + upperRight;

        String bottomLine = bottomLeft;
        for (int i = 0; i<7; i++){
            bottomLine += horizontal3 + bottomT;
        }
        bottomLine += horizontal3 + bottomRight;
        chess+=topLine + "\n";

        for (int row = 7; row >=0; row--){
            String midLine = "";
            for (int col = 0; col < 8; col++){
                if(board[row][col]==null) {
                    midLine += verticalLine + " \u3000 ";
                } else {midLine += verticalLine + " "+board[row][col]+" ";}
            }
            midLine += verticalLine;
            String midLine2 = leftT;
            for (int i = 0; i<7; i++){
                midLine2 += horizontal3 + plus;
            }
            midLine2 += horizontal3 + rightT;
            chess+=midLine+ "\n";
            if(row>=1)
                chess+=midLine2+ "\n";
        }

        chess+=bottomLine;
        return chess;
    }
    
    public static void main(String args[]) {
        /*
         * This main method is provided in the A2.pdf for testing purposes only.
         */

    }
}
