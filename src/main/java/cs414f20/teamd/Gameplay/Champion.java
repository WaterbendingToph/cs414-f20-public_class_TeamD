package cs414f20.teamd.Gameplay;

import java.util.ArrayList;

public class Champion extends ChessPiece {

    public Champion(ChessBoard board, Color color) {
        super(board, color);
    }

    /**
     * This concrete class implements the methods for the abstract class ChessPiece
     * for the champion piece in this variant of chess. The champion can move 1-2
     * spaces in the cardinal directions, or exactly two spaces diagonally.
     */

    @Override
    public String toString() {
        if (color == ChessPiece.Color.WHITE)
            return "\u2616";
        else
            return "\u2617";
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
        for (String option : boundedOptions) {
            try {
                ChessPiece testPiece = this.board.getPiece(option);

                if (testPiece != null)
                    if (testPiece.color == this.color)
                        continue;

                legalMoves.add(option);
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
    }//DUPLICATION WITH WIZARD
    private ArrayList<JavaDoesntHaveTuples> ValidMoveOffsets() {
        ArrayList<JavaDoesntHaveTuples> javaOverheadCanBeGross = new ArrayList<JavaDoesntHaveTuples>();

        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(-2, -2));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(-2, 0));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(-2, 2));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(-1, 0));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(0, -2));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(0, -1));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(0, 0));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(0, 1));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(0, 2));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(1, 0));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(2, -2));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(2, 0));
        javaOverheadCanBeGross.add(new JavaDoesntHaveTuples(2, 2));

        return javaOverheadCanBeGross;
    }//DUPLICATION WITH WIZARD
}
