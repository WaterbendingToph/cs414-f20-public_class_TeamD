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
        for (int i = 0; i < unboundedOptions.size(); i++)
            try {
                boundedOptions.add(Helper.boundedMove(position, unboundedOptions.get(i).x, unboundedOptions.get(i).y));
            } catch (IllegalPositionException e) {}

        ArrayList<String> legalMoves = new ArrayList<String>();
        for (String option : boundedOptions) {
            try {
                ChessPiece testPiece = this.board.getPiece(option);

                if (MoveOptionIsCardinalAndTwoAway(position, option)) {
                    String spaceBetween = SpaceBetweenTwoSpacesThatAreTwoAway(position, option);
                    if (this.board.getPiece(spaceBetween) != null)
                        continue;
                }

                if (testPiece != null) {
                    if (testPiece.color == this.color)
                        continue;
                    else
                        legalMoves.add(option);
                }
                else
                    legalMoves.add(option);
            } catch (IllegalPositionException e) {}
        }

        return legalMoves;
    }

    private boolean MoveOptionIsCardinalAndTwoAway(String position, String option) {
        char posX = position.charAt(0);
        char posY = position.charAt(1);
        char optX = option.charAt(0);
        char optY = option.charAt(1);

        if (posX == 'w' || optX == 'w')
            return false;

        return (Math.abs(posX - optX) == 2 ^ Math.abs(posY - optY) == 2);
    }
    private String SpaceBetweenTwoSpacesThatAreTwoAway(String position, String option) {
        char posX = position.charAt(0);
        char posY = position.charAt(1);
        char optX = option.charAt(0);
        char optY = option.charAt(1);

        return "" + ((char) ((posX + optX) / 2)) + ((char) ((posY + optY) / 2));
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
