package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(ChessBoard board, ChessPiece.Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        if (color == ChessPiece.Color.WHITE)
            return "\u2657";
        else
            return "\u265D";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<>();
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

        ArrayList<ArrayList<String>> potentialPaths = new ArrayList<>();
        for (int diagonalDirection = 0; diagonalDirection < 4; diagonalDirection++){
            potentialPaths.add(new ArrayList<>());

            for (int squaresMoved = 1; squaresMoved < 8; squaresMoved++) {//Get all possible moves on board.
                try {
                    int xChange = (1 - 2 * (diagonalDirection / 2)) * squaresMoved;
                    int yChange = (1 - 2 * (diagonalDirection % 2)) * squaresMoved;
                    potentialPaths.get(diagonalDirection).add(Helper.boundedMove(currentPosition, xChange, yChange));
                } catch (IllegalPositionException ipe) { /* intentional do nothing */ }
            }
        }

        return potentialPaths;
    }
    private ArrayList<String> pathLegalMoves(ArrayList<String> path) {
        ArrayList<String> legalMoves = new ArrayList<>();

        for (int squareIndex = 0; squareIndex < path.size(); squareIndex++) {
            String position = path.get(squareIndex);

            try {
                if (!Helper.positionIsEmpty(board, position)) {
                    ChessPiece checkPiece = board.getPiece(position);

                    int inclusiveIndex = 0;
                    if (checkPiece.color != this.color)
                        inclusiveIndex = 1;

                    List<String> swap = path.subList(0, squareIndex + inclusiveIndex);
                    path = new ArrayList<>();
                    path.addAll(swap);
                }
            } catch (IllegalPositionException ipe) { /* intentional do nothing */ }

            if (squareIndex < path.size())
                legalMoves.add(position);
        }

        return legalMoves;
    }
}
