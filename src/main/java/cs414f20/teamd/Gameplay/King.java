package cs414f20.teamd.Gameplay;

import java.util.ArrayList;

public class King extends ChessPiece {

    public King(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        if (color == Color.WHITE)
            return "\u2654";
        else
            return "\u265A";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<String>();

        String currentPosition = getPosition();
        for (int col = -1; col < 2; col++)
            for (int row = -1; row < 2; row++)
                try {
                    String newPosition = Helper.boundedMove(currentPosition, col, row);
                    if (Helper.positionIsEmpty(board, newPosition))
                        legalMoves.add(newPosition);
                    else if (board.getPiece(newPosition).color != color)
                        legalMoves.add(newPosition);
                } catch (IllegalPositionException e) {}

        return legalMoves;
    }

    public boolean inCheck() {
        // Pseudocode outline
        /* enemies = AllEnemies();
         * safe = True
         *
         * for enemy in enemies:
         *   if this.getPosition() in enemy.legalMoves():
         *     safe = False
         *
         * return !safe
         */

        return false;
    }
}
