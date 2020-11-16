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

    @Override
    public String toString() {
        if (color == ChessPiece.Color.WHITE)
            return "\u2616";
        else
            return "\u2617";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<String>();

        //

        return legalMoves;
    }
}
