package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ChessPiece {
    public enum Color {WHITE, BLACK};
    protected ChessBoard board;
    protected int row;
    protected int column;
    protected Color color;

    private static final List<Character> validLetters = Arrays.asList(new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'});//HEAVY DUPLICATION WITH ChessBoard
    private static final List<Character> validNumbers = Arrays.asList(new Character[]{'1', '2', '3', '4', '5', '6', '7', '8'});


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

    public void setPosition(String position) throws IllegalPositionException {//HEAVY DUPLICATION WITH ChessBoard
        if (!Helper.isBoundedPosition(position))
            throw new IllegalPositionException();

        char positionLetter = position.charAt(0);
        char positionNumber = position.charAt(1);

        row = validNumbers.indexOf((char) positionNumber);
        column = validLetters.indexOf((char) positionLetter);
    }

    abstract public String toString();

    abstract public ArrayList<String> legalMoves();
}
