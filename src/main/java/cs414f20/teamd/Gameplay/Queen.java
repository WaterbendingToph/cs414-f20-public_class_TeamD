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
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<>();

        ArrayList<ArrayList<String>> potentialMovePaths = createAllPotentialPaths();
        for (ArrayList<String> path : potentialMovePaths) {
            ArrayList<String> moves = Helper.pathLegalMoves(path, this);
            legalMoves.addAll(moves);
        }

        return legalMoves;
    }

    // helper methods
    private ArrayList<ArrayList<String>> createAllPotentialPaths() {
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        result.addAll(createDiagonalPotentialPaths() );
        result.addAll(createCardinalPotentialPaths() );

        return result;
    }

    private ArrayList<ArrayList<String>> createDiagonalPotentialPaths(){
        String currentPosition = getPosition();

        ArrayList<ArrayList<String>> potentialPaths = new ArrayList<>();
        for (int diagonalDirection = 0; diagonalDirection < 4; diagonalDirection++){
            ArrayList<String> newPath = new ArrayList<>();

            for (int squaresMoved = 1; squaresMoved < 12; squaresMoved++) {//Get all possible moves on board.
                try {
                    int xChange = (1 - 2 * (diagonalDirection / 2)) * squaresMoved;
                    int yChange = (1 - 2 * (diagonalDirection % 2)) * squaresMoved;
                    newPath.add(Helper.boundedMove(currentPosition, xChange, yChange));
                } catch (IllegalPositionException ipe) { /* intentional do nothing */ }
            }

            potentialPaths.add(newPath);
        }

        return potentialPaths;
    }

    private ArrayList<ArrayList<String>> createCardinalPotentialPaths() {
        String currentPosition = getPosition();

        ArrayList<ArrayList<String>> potentialPaths = new ArrayList<>();
        for (int cardinalDirection = 0; cardinalDirection < 4; cardinalDirection++) {
            ArrayList<String> newPath = new ArrayList<>();

            for (int magnitude = 1; magnitude < 10; magnitude++) {
                try {
                    int xChange = (cardinalDirection % 2) * (1 - 2 * (cardinalDirection / 2)) * magnitude;
                    int yChange = ((cardinalDirection + 1) % 2) * (-1 + 2 * (cardinalDirection / 2)) * magnitude;
                    newPath.add(Helper.boundedMove(currentPosition, xChange, yChange));
                } catch (IllegalPositionException ipe) { /* intentional do nothing */ }
            }

            potentialPaths.add(newPath);
        }

        return potentialPaths;
    }

}
