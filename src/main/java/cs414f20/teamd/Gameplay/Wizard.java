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
        ArrayList<JavaDoesntHaveTuples> unboundedOptions = ValidMoveOffsets();

        String position = this.getPosition();
        ArrayList<String> boundedOptions = new ArrayList<String>();
        for (JavaDoesntHaveTuples unboundedOption : unboundedOptions)
            try {
                boundedOptions.add(Helper.boundedMove(position, unboundedOption.x, unboundedOption.y));
            } catch (IllegalPositionException e) {}

        ArrayList<String> legalMoves = new ArrayList<String>();
        for (String boundedOption : boundedOptions) {
            try {
                ChessPiece testPiece = this.board.getPiece(boundedOption);

                if (testPiece != null)
                    if (testPiece.color == this.color)
                        continue;

                legalMoves.add(boundedOption);
            } catch (IllegalPositionException e) {}
        }

        return legalMoves;
    }

    private class JavaDoesntHaveTuples {
        public int x;
        public int y;

        public JavaDoesntHaveTuples(int newX, int newY) {
            x = newX;
            y = newY;
        }
    }//DUPLICATION WITH CHAMPION
    private ArrayList<JavaDoesntHaveTuples> ValidMoveOffsets() {
        ArrayList<JavaDoesntHaveTuples> javaOverheadCanBeGross = new ArrayList<JavaDoesntHaveTuples>();

        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(-3, -1));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(-3, 1));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(-1, -3));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(-1, -1));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(-1, 1));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(-1, 3));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(1, -3));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(1, -1));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(1, 1));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(1, 3));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(3, -1));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(3, 1));

        return javaOverheadCanBeGross;
    }//DUPLICATION WITH CHAMPION
}
