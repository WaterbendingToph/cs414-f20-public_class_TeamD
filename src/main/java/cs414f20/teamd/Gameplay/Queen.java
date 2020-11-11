package cs414f20.teamd.Gameplay;

import java.util.ArrayList;

public class Queen extends ChessPiece {

    public Queen(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        if (color == Color.WHITE)
            return "\u2655";
        else
            return "\u265B";
    }

    @Override
    public ArrayList<String> legalMoves() {//TODO: Was not implemented for A2. So... implement.
        return new ArrayList<String>();
    }
}
