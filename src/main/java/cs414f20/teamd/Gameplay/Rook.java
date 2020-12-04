package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {

    public Rook(ChessBoard board, ChessPiece.Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        if (color == ChessPiece.Color.WHITE)
            return "\u2656";
        else
            return "\u265C";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<>();
        ArrayList<ArrayList<String>> potentialMovePaths = createPotentialPaths();

        for (ArrayList<String> path : potentialMovePaths) {
            ArrayList<String> moves = Helper.pathLegalMoves(path, this);
            legalMoves.addAll(moves);
        }

        return legalMoves;
    }

    //Helper methods
    private ArrayList<ArrayList<String>> createPotentialPaths(){
        String currentPosition = getPosition();

        ArrayList<ArrayList<String>> potentialPaths = new ArrayList<>();
        for (int cardinalDirection = 0; cardinalDirection < 4; cardinalDirection++) {
            potentialPaths.add(new ArrayList<>());

            for (int magnitude = 1; magnitude < 12; magnitude++) {
                try {
                    int xChange = (cardinalDirection       % 2) * ( 1 - 2 * (cardinalDirection / 2)) * magnitude;
                    int yChange = ((cardinalDirection + 1) % 2) * (-1 + 2 * (cardinalDirection / 2)) * magnitude;
                    potentialPaths.get(cardinalDirection).add(Helper.boundedMove(currentPosition, xChange, yChange));
                } catch (IllegalPositionException ipe) { /* intentional do nothing */ }
            }
        }

        return potentialPaths;
    }
}
