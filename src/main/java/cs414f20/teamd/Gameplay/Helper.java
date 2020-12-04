package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {
    public static final List<Character> validLetters = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'w');
    public static final List<Character> validNumbers = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    public static final List<Character> validWizardNumbers = Arrays.asList('1', '2', '3', '4');

    //Helper methods
    public static boolean positionIsEmpty(ChessBoard board, String position) throws IllegalPositionException {
        if (!Helper.isBoundedPosition(position))
            throw new IllegalPositionException();

        return board.getPiece(position) == null;
    }

    public static boolean isBoundedPosition(String position) {
        if (position.length() != 2)
            return false;

        char positionLetter = position.charAt(0);
        char positionNumber = position.charAt(1);

        if (positionLetter == 'w') {
            return validWizardNumbers.contains(positionNumber);
        }

        return validLetters.contains(positionLetter) && validNumbers.contains(positionNumber);
    }

    public static String boundedMove(String startPosition, int xChange, int yChange) throws IllegalPositionException {
        if (!isBoundedPosition(startPosition))
            throw new IllegalPositionException();

        char x = startPosition.charAt(0);
        char y = startPosition.charAt(1);
        String newPosition;

        if (x == 'w')
            return boundedMoveFromWizardSquare(y, xChange, yChange);

        x = (char) ((int)x + xChange);
        y = (char) ((int)y + yChange);

        if (hasCornerCharacters(x,y) )
            newPosition = translateCornerCharacters(x,y);
        else
            newPosition = "" + x + y;

        if (!isBoundedPosition(newPosition))
            throw new IllegalPositionException();

        return newPosition;
    }

    private static String boundedMoveFromWizardSquare(char y, int xChange , int yChange) throws IllegalPositionException{
        String newPosition = "";

        if (y == '1') {
            newPosition += 'a' + "" + '0';
            return boundedMove(newPosition, xChange - 1, yChange - 1);
        }
        if (y == '2') {
            newPosition += 'j' + "" + '0';
            return boundedMove(newPosition, xChange + 1, yChange - 1);
        }
        if (y == '3') {
            newPosition += 'j' + "" + '9';
            return boundedMove(newPosition, xChange + 1, yChange + 1);
        }
        if (y == '4') {
            newPosition += 'a' + "" + '9';
            return boundedMove(newPosition, xChange - 1, yChange + 1);
        }

        throw new IllegalPositionException();
    }

    private static Boolean hasCornerCharacters(char x, char y){
        return (x == '`' || x == 'k') && (y == '/' || y == ':');
    }

    private static String translateCornerCharacters(char x, char y) throws IllegalPositionException{
        if (x == '`' && y == '/')
            return "w1";
        else if (x == '`' && y == ':')
            return "w4";
        else if (x == 'k' && y == '/')
            return "w2";
        else if (x == 'k' && y == ':')
            return "w3";
        else
            throw new IllegalPositionException();
    }

    public static String arrayIndicesToPosition(int row, int column) throws IllegalPositionException {
        String position = ("" + (char)('a' + column)) + ("" + (char)('0' + row));

        if (arrayIndicesReferenceCorners(row, column))
            position = cornerArrayIndicesToPosition(row, column);

        if (!isBoundedPosition(position))
            throw new IllegalPositionException();

        return position;
    }

    private static boolean arrayIndicesReferenceCorners(int row, int column) {
        return (row == 10 || row == 11) && (column == 0 || column == 1);
    }

    private static String cornerArrayIndicesToPosition(int row, int column) {
        int switcher = row * 100 + column;

        switch(switcher) {
            case 1000:
                return "w1";
            case 1001:
                return "w2";
            case 1100:
                return "w3";
            case 1101:
                return "w4";
            default: return "error";
        }

    }


    protected static ArrayList<String> pathLegalMoves(ArrayList<String> path, ChessPiece checkingPiece) {
        ArrayList<String> legalMoves = new ArrayList<>();

        for (int indexOfSquareInPath = 0; indexOfSquareInPath < path.size(); indexOfSquareInPath++) {
            String position = path.get(indexOfSquareInPath);

            try {
                if (!positionIsEmpty(checkingPiece.board, position)) {
                    ChessPiece checkPiece = checkingPiece.board.getPiece(position);

                    int inclusiveIndex = 0;
                    if (checkPiece.color != checkingPiece.color)
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

    protected static class PseudoTuple {
        public int x;
        public int y;

        public PseudoTuple(int newX, int newY) {
            x = newX;
            y = newY;
        }
    }
}
