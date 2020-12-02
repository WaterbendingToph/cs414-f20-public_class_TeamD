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
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<>();
        int[][] legalMoveDistances = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

            for (int i = 0; i < 8; i++) {
                try {
                    String potentialMove = Helper.boundedMove(getPosition(), legalMoveDistances[i][0], legalMoveDistances[i][1]);

                    ChessPiece testPiece = board.getPiece(potentialMove);

                    if (testPiece != null)
                        if (testPiece.color == color)
                            continue;

                    legalMoves.add(potentialMove);
                } catch (IllegalPositionException ipe) { /* intentional do nothing */ }
            }

        return legalMoves;
    }
}
