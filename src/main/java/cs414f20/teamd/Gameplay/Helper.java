package cs414f20.teamd.Gameplay;

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

        Character positionLetter = position.charAt(0);
        Character positionNumber = position.charAt(1);

        if (positionLetter == 'w') {
            if (validWizardNumbers.contains(positionNumber) )
                return true;
            return false;
        }

        return validLetters.contains(positionLetter) && validNumbers.contains(positionNumber);
    }

    public static String boundedMove(String startPosition, int xChange, int yChange) throws IllegalPositionException {
        if (!isBoundedPosition(startPosition))
            throw new IllegalPositionException();

        char x = startPosition.charAt(0);
        char y = startPosition.charAt(1);

        x = (char) ((int)x + xChange);
        y = (char) ((int)y + yChange);

        String newPosition = "" + x + y;

        if (!isBoundedPosition(newPosition))
            throw new IllegalPositionException();

        return newPosition;
    }

    public static String arrayIndicesToPosition(int row, int column) throws IllegalPositionException {
        String position = ("" + (char)('a' + column)) + ("" + (char)('1' + row));

        if (!isBoundedPosition(position))
            throw new IllegalPositionException();

        return position;
    }
}
