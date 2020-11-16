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

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<String>();

        //

        return legalMoves;
    }
}
