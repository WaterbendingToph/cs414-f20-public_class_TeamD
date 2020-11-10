package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {
    private ChessPiece[][] board;
    private static final List<Character> validLetters = Arrays.asList(new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'});//HEAVY DUPLICATION WITH ChessPiece
    private static final List<Character> validNumbers = Arrays.asList(new Character[]{'1', '2', '3', '4', '5', '6', '7', '8'});


    ChessBoard() {
        board = new ChessPiece[8][8];
    }

    public void initialize(){
        initializePawns();
        initializeCastleFolk();
    }
    private void initializePawns(){
        for (int column = 0; column < 8; column++){
            try {
                int row = 6;
                Pawn pawn = new Pawn(this, ChessPiece.Color.BLACK);
                board[row][column] = pawn;
                pawn.setPosition(Helper.arrayIndicesToPosition(row, column));

                row = 1;
                pawn = new Pawn(this, ChessPiece.Color.WHITE);
                board[row][column] = pawn;
                pawn.setPosition(Helper.arrayIndicesToPosition(row, column));
            } catch (IllegalPositionException ipe){}
        }
    }
    private void initializeCastleFolk() {
        ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
        ChessPiece.Color color = ChessPiece.Color.BLACK;
        for (int i = 0; i < 2; i++){
            pieces.add(new Rook(this, color));
            pieces.add(new Knight(this, color));
            pieces.add(new Bishop(this, color));
            pieces.add(new Queen(this, color));
            pieces.add(new King(this, color));
            pieces.add(new Bishop(this, color));
            pieces.add(new Knight(this, color));
            pieces.add(new Rook(this, color));

            for (int j = 0; j < 8; j++){
                int row = 7 * (1 - i);
                ChessPiece piece = pieces.get(j);

                try {
                    board[row][j] = piece;
                    piece.setPosition(Helper.arrayIndicesToPosition(row, j));
                } catch (IllegalPositionException ipe) {}
            }

            pieces.removeAll(pieces);
            color = ChessPiece.Color.WHITE;
        }
    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {//HEAVY DUPLICATION WITH ChessPiece
        if (!Helper.isBoundedPosition(position))
            throw new IllegalPositionException();

        char positionLetter = position.charAt(0);
        char positionNumber = position.charAt(1);

        int row = validNumbers.indexOf((char) positionNumber);
        int column = validLetters.indexOf((char) positionLetter);

        return board[row][column];
    }//HEAVY DUPLICATION WITH "position" TO board[][] CONVERSION.

    public boolean placePiece(ChessPiece piece, String position){//HEAVY DUPLICATION WITH "position" TO board[][] CONVERSION.
        try {
            if (!Helper.positionIsEmpty(this, position))
                return false;
        } catch (IllegalPositionException ipe){
            return false;
        }

        char positionLetter = position.charAt(0);
        char positionNumber = position.charAt(1);

        int row = validNumbers.indexOf((char) positionNumber);
        int column = validLetters.indexOf((char) positionLetter);

        board[row][column] = piece;
        try { piece.setPosition(position); } catch (IllegalPositionException e) {}

        return true;
    }//HEAVY DUPLICATION WITH "position" TO board[][] CONVERSION.

    public void move(String fromPosition, String toPosition) throws IllegalMoveException {//HEAVY DUPLICATION WITH "position" TO board[][] CONVERSION.
        try {
            if (Helper.positionIsEmpty(this, fromPosition) || !Helper.isBoundedPosition(toPosition))
                throw new IllegalMoveException();

            ChessPiece piece = getPiece(fromPosition);
            ArrayList<String> moves = piece.legalMoves();

            if (moves.size() == 0 || !moves.contains(toPosition))
                throw new IllegalMoveException();

            char positionLetter = fromPosition.charAt(0);//HEAVY DUPLICATION WITH "position" TO board[][] CONVERSION.
            char positionNumber = fromPosition.charAt(1);
            int fromRow = validNumbers.indexOf((char) positionNumber);
            int fromCol = validLetters.indexOf((char) positionLetter);
            positionLetter = toPosition.charAt(0);
            positionNumber = toPosition.charAt(1);
            int toRow = validNumbers.indexOf((char) positionNumber);
            int toCol = validLetters.indexOf((char) positionLetter);//HEAVY DUPLICATION WITH "position" TO board[][] CONVERSION.

            board[toRow][toCol] = board[fromRow][fromCol];
            board[fromRow][fromCol] = null;

            piece.setPosition(toPosition);
        } catch (IllegalPositionException e) {
            throw new IllegalMoveException(); }
    }//HEAVY DUPLICATION WITH "position" TO board[][] CONVERSION.

    public String toString(){
        // From the Canvas page: https://colostate.instructure.com/courses/106587/files/16341487/download?wrap=1
        String chess = "";
        String upperLeft = "\u250C";
        String upperRight = "\u2510";
        String horizontalLine = "\u2500";
        String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
        String verticalLine = "\u2502";
        String upperT = "\u252C";
        String bottomLeft = "\u2514";
        String bottomRight = "\u2518";
        String bottomT = "\u2534";
        String plus = "\u253C";
        String leftT = "\u251C";
        String rightT = "\u2524";
        String topLine = upperLeft;
        for (int i = 0; i < 7; i++) {
            topLine += horizontal3 + upperT;
        }
        topLine += horizontal3 + upperRight;
        String bottomLine = bottomLeft;
        for (int i = 0; i < 7; i++) {
            bottomLine += horizontal3 + bottomT;
        }
        bottomLine += horizontal3 + bottomRight;
        chess += topLine + "\n";
        for (int row = 7; row >= 0; row--) {
            String midLine = "";
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == null) {
                    midLine += verticalLine + " \u3000 ";
                } else {
                    midLine += verticalLine + " " + board[row][col] + " ";
                }
            }
            midLine += verticalLine;
            String midLine2 = leftT;
            for (int i = 0; i < 7; i++) {
                midLine2 += horizontal3 + plus;
            }
            midLine2 += horizontal3 + rightT;
            chess += midLine + "\n";
            if (row >= 1) chess += midLine2 + "\n";
        }
        chess += bottomLine;
        return chess;
    }

    //Taken from the assignment specification
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.initialize();
        System.out.println(board);
        try{
            board.move("c2", "c4");
        } catch (IllegalMoveException ime){}
        System.out.println(board);
    }
}
