package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {
    public enum Color {WHITE, BLACK}
    protected ChessBoard board;
    protected int row;
    protected int column;
    protected Color color;

    private static final List<Character> validLetters = Helper.validLetters;
    private static final List<Character> validNumbers = Helper.validNumbers;


    public ChessPiece(ChessBoard board, Color color){
        this.board = board;
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public String getPosition(){
        char letter;
        char number;

        if (row > 9) {
            letter = 'w';
            if (row == 10 && column == 0)
                number = 1;
            else if (row == 10 && column == 1)
                number = 2;
            else if (row == 11 && column == 0)
                number = 3;
            else /* row == 11 && column == 1, written this way to kill warning */
                number = 4;
        } else {
            letter = (char) ((char) (column) + 'a');
            number = (char) ((char) (row) + '1');
        }

        return "" + letter + number;
    }

    public void setPosition(String position) throws IllegalPositionException {
        if (!Helper.isBoundedPosition(position))
            throw new IllegalPositionException();

        if (position.charAt(0) == 'w') {
            switch (position.charAt(1)) {
                case '1':
                    row = 10;
                    column = 0;
                    return;
                case '2':
                    row = 10;
                    column = 1;
                    return;
                case '3':
                    row = 11;
                    column = 0;
                    return;
                case '4':
                    row = 11;
                    column = 1;
                    return;
            }
        }

        char positionLetter = position.charAt(0);
        char positionNumber = position.charAt(1);

        row = validNumbers.indexOf(positionNumber);
        column = validLetters.indexOf(positionLetter);
    }

    abstract public String toString();

    abstract public ArrayList<String> legalMoves();
}
