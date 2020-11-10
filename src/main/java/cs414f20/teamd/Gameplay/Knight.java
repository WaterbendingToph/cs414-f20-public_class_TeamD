package cs414f20.teamd.Gameplay;

import java.util.ArrayList;

public class Knight extends ChessPiece {

    public Knight(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        if (color == Color.WHITE)
            return "\u2658";
        else
            return "\u265E";
    }

    @Override
    public ArrayList<String> legalMoves() {//TODO: Was not implemented for A2. So... implement.
        return new ArrayList<String>();
    }
}
