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
        char letter = (char) ((char)(column) + 'a');
        char number = (char) ((char)(row) + '1');

        return "" + letter + number;
    }

    public void setPosition(String position) throws IllegalPositionException {
        if (!Helper.isBoundedPosition(position))
            throw new IllegalPositionException();

        char positionLetter = position.charAt(0);
        char positionNumber = position.charAt(1);

        row = validNumbers.indexOf(positionNumber);
        column = validLetters.indexOf(positionLetter);
    }

    abstract public String toString();

    abstract public ArrayList<String> legalMoves();
}
