package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<ArrayList<ArrayList<String>>> potentialMovePaths = createAllPotentialPaths();

        for (ArrayList<ArrayList<String>> setOfPotentialPaths : potentialMovePaths) {
            for (ArrayList<String> path : setOfPotentialPaths) {
                ArrayList<String> moves = pathLegalMoves(path);
                legalMoves.addAll(moves);
            }
        }
        return legalMoves;
    }

    // helper methods
    private ArrayList<ArrayList<ArrayList<String>>> createAllPotentialPaths() {
        ArrayList<ArrayList<ArrayList<String>>> result = new ArrayList<>();

        result.add(createDiagonalPotentialPaths() );
        result.add(createCardinalPotentialPaths() );

        return result;
    }

    private ArrayList<ArrayList<String>> createDiagonalPotentialPaths(){
        String currentPosition = getPosition();

        ArrayList<ArrayList<String>> potentialPaths = new ArrayList<>();
        for (int diagonalDirection = 0; diagonalDirection < 4; diagonalDirection++){
            potentialPaths.add(new ArrayList<>());

            for (int squaresMoved = 1; squaresMoved < 12; squaresMoved++) {//Get all possible moves on board.
                try {
                    int xChange = (1 - 2 * (diagonalDirection / 2)) * squaresMoved;
                    int yChange = (1 - 2 * (diagonalDirection % 2)) * squaresMoved;
                    potentialPaths.get(diagonalDirection).add(Helper.boundedMove(currentPosition, xChange, yChange));
                } catch (IllegalPositionException ipe) { /* intentional do nothing */ }
            }
        }

        return potentialPaths;
    }

    private ArrayList<ArrayList<String>> createCardinalPotentialPaths() {
        String currentPosition = getPosition();

        ArrayList<ArrayList<String>> potentialPaths = new ArrayList<>();
        for (int cardinalDirection = 0; cardinalDirection < 4; cardinalDirection++) {
            potentialPaths.add(new ArrayList<>());

            for (int magnitude = 1; magnitude < 8; magnitude++) {
                try {
                    int xChange = (cardinalDirection % 2) * (1 - 2 * (cardinalDirection / 2)) * magnitude;
                    int yChange = ((cardinalDirection + 1) % 2) * (-1 + 2 * (cardinalDirection / 2)) * magnitude;
                    potentialPaths.get(cardinalDirection).add(Helper.boundedMove(currentPosition, xChange, yChange));
                } catch (IllegalPositionException ipe) { /* intentional do nothing */ }
            }
        }

        return potentialPaths;
    }

    private ArrayList<String> pathLegalMoves(ArrayList<String> path) {
        ArrayList<String> legalMoves = new ArrayList<>();

        for (int indexOfSquareInPath = 0; indexOfSquareInPath < path.size(); indexOfSquareInPath++) {
            String position = path.get(indexOfSquareInPath);

            try {
                if (!Helper.positionIsEmpty(board, position)) {
                    ChessPiece checkPiece = board.getPiece(position);

                    int inclusiveIndex = 0;
                    if (checkPiece.color != this.color)
                        inclusiveIndex = 1;

                    List<String> swap = path.subList(0, indexOfSquareInPath + inclusiveIndex);
                    path = new ArrayList<>();
                    path.addAll(swap);
                }
            } catch (IllegalPositionException ipe) { /* intentional do nothing */ }

            if (indexOfSquareInPath < path.size())
                legalMoves.add(position);
        }

        return legalMoves;
    }

}
