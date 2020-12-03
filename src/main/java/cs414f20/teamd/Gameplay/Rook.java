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
        ArrayList<String> legalMoves = new ArrayList<String>();
        ArrayList<ArrayList<String>> potentialMovePaths = createPotentialPaths();

        for (ArrayList<String> path : potentialMovePaths) {
            ArrayList<String> moves = pathLegalMoves(path);
            legalMoves.addAll(moves);
        }

        return legalMoves;
    }

    //Helper methods
    private ArrayList<ArrayList<String>> createPotentialPaths(){
        String currentPosition = getPosition();

        ArrayList<ArrayList<String>> potentialPaths = new ArrayList<ArrayList<String>>();
        for (int cardinalDirection = 0; cardinalDirection < 4; cardinalDirection++) {
            potentialPaths.add(new ArrayList<String>());

            for (int magnitude = 1; magnitude < 10; magnitude++) {
                try {
                    int xChange = (cardinalDirection       % 2) * ( 1 - 2 * (cardinalDirection / 2)) * magnitude;
                    int yChange = ((cardinalDirection + 1) % 2) * (-1 + 2 * (cardinalDirection / 2)) * magnitude;
                    potentialPaths.get(cardinalDirection).add(Helper.boundedMove(currentPosition, xChange, yChange));
                } catch (IllegalPositionException ipe) {}
            }
        }

        return potentialPaths;
    }
    private ArrayList<String> pathLegalMoves(ArrayList<String> path) {
        ArrayList<String> legalMoves = new ArrayList<String>();

        for (int indexOfSquareInPath = 0; indexOfSquareInPath < path.size(); indexOfSquareInPath++) {
            String position = path.get(indexOfSquareInPath);

            try {
                if (!Helper.positionIsEmpty(board, position)) {
                    ChessPiece checkPiece = board.getPiece(position);

                    int inclusiveIndex = 0;
                    if (checkPiece.color != this.color)
                        inclusiveIndex = 1;

                    List<String> swap = path.subList(0, indexOfSquareInPath + inclusiveIndex);
                    path = new ArrayList<String>();
                    path.addAll(swap);
                }
            } catch (IllegalPositionException ipe) {}

            if (indexOfSquareInPath < path.size())
                legalMoves.add(position);
        }

        return legalMoves;
    }
}
