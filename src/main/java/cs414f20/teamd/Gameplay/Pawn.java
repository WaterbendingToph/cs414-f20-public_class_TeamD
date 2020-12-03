package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends ChessPiece {
    int pawnDirection;
    List<String> startingPositions;

    public Pawn(ChessBoard board, Color color) {
        super(board, color);
        pawnDirectionSetup();
    }
    private void pawnDirectionSetup(){
        if (color == Color.WHITE) {
            pawnDirection = 1;
            startingPositions = Arrays.asList(new String[]{"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"});
        } else {
            pawnDirection = -1;
            startingPositions = Arrays.asList(new String[]{"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"});
        }
    }

    @Override
    public String toString() {
        if (color == Color.WHITE)
            return "\u2659";
        else
            return "\u265F";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<>();


        forwardLegalMoves(legalMoves);
        diagonalLegalMoves(legalMoves);

        return legalMoves;
    }
    private void forwardLegalMoves(ArrayList<String> legalMoves){
        String checkPosition;
        String currentPosition = getPosition();

        try {
            checkPosition = Helper.boundedMove(currentPosition, 0, pawnDirection);
            if (Helper.positionIsEmpty(board, checkPosition))
                legalMoves.add(checkPosition);

            if (startingPositions.contains(currentPosition)) {
                checkPosition = Helper.boundedMove(currentPosition, 0, pawnDirection * 2);
                if (Helper.positionIsEmpty(board, checkPosition))
                    legalMoves.add(checkPosition);

                checkPosition = Helper.boundedMove(currentPosition, 0, pawnDirection * 3);
                if (Helper.positionIsEmpty(board, checkPosition))
                    legalMoves.add(checkPosition);
            }
        } catch (IllegalPositionException ipe) { /* intentional do nothing */ }
    }
    private void diagonalLegalMoves(ArrayList<String> legalMoves){
        String checkPosition;
        String currentPosition = getPosition();

        try {
            checkPosition = Helper.boundedMove(currentPosition, -1, pawnDirection);
            if (!Helper.positionIsEmpty(board, checkPosition))
                if (board.getPiece(checkPosition).color != color)
                    legalMoves.add(checkPosition);
        } catch (IllegalPositionException ipe) { /* intentional do nothing */ }

        try {
            checkPosition = Helper.boundedMove(currentPosition, 1, pawnDirection);
            if (!Helper.positionIsEmpty(board, checkPosition))
                if (board.getPiece(checkPosition).color != color)
                    legalMoves.add(checkPosition);
        } catch (IllegalPositionException ipe) { /* intentional do nothing */ }
    }
}
