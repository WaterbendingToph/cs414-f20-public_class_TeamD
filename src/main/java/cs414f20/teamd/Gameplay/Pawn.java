package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends ChessPiece {
    int pawnDirection;
    ArrayList<String> startingPositions;

    public Pawn(ChessBoard board, Color color) {
        super(board, color);
        pawnDirectionSetup();
    }
    private void pawnDirectionSetup(){
        if (color == Color.WHITE) {
            pawnDirection = 1;
            startingPositions = new ArrayList<String>(Arrays.asList(new String[]{"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"}));
        } else {
            pawnDirection = -1;
            startingPositions = new ArrayList<String>(Arrays.asList(new String[]{"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"}));
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
        ArrayList<String> legalMoves = new ArrayList<String>();
        String currentPosition = getPosition();

        forwardLegalMoves(legalMoves);
        diagonalLegalMoves(legalMoves);

        return legalMoves;
    }
    private void forwardLegalMoves(ArrayList<String> legalMoves){
        String checkPosition = "";
        String currentPosition = getPosition();

        try {
            checkPosition = Helper.boundedMove(currentPosition, 0, pawnDirection);
            if (Helper.positionIsEmpty(board, checkPosition))
                legalMoves.add(checkPosition);

            if (startingPositions.contains(currentPosition)) {
                checkPosition = Helper.boundedMove(currentPosition, 0, pawnDirection * 2);
                if (Helper.positionIsEmpty(board, checkPosition))
                    legalMoves.add(checkPosition);
            }
        } catch (IllegalPositionException ipe) {}
    }
    private void diagonalLegalMoves(ArrayList<String> legalMoves){
        String checkPosition = "";
        String currentPosition = getPosition();

        try {
            checkPosition = Helper.boundedMove(currentPosition, -1, pawnDirection);
            if (!Helper.positionIsEmpty(board, checkPosition))
                if (board.getPiece(checkPosition).color != color)
                    legalMoves.add(checkPosition);
        } catch (IllegalPositionException ipe) {}

        try {
            checkPosition = Helper.boundedMove(currentPosition, 1, pawnDirection);
            if (!Helper.positionIsEmpty(board, checkPosition))
                if (board.getPiece(checkPosition).color != color)
                    legalMoves.add(checkPosition);
        } catch (IllegalPositionException ipe) {}
    }

}
