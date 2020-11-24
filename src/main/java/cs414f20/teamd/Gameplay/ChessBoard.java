package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private ChessPiece[][] board;
    private static final List<Character> validLetters = Helper.validLetters;
    private static final List<Character> validNumbers = Helper.validNumbers;


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
            } catch (IllegalPositionException ipe){ /* intentional do nothing */ }
        }
    }
    private void initializeCastleFolk() {
        ArrayList<ChessPiece> pieces = new ArrayList<>();
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
                } catch (IllegalPositionException ipe) { /* intentional do nothing */ }
            }

            pieces.removeAll(pieces);
            color = ChessPiece.Color.WHITE;
        }
    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {
        if (!Helper.isBoundedPosition(position))
            throw new IllegalPositionException();

        int[] boardPositions = convertPositionToBoardIndices(position);

        return board[boardPositions[0]] [boardPositions[1]];
    }
    public boolean placePiece(ChessPiece piece, String position){
        try {
            if (!Helper.positionIsEmpty(this, position))
                return false;
        } catch (IllegalPositionException ipe){
            return false;
        }

        int[] boardPositions = convertPositionToBoardIndices(position);

        board[boardPositions[0]] [boardPositions[1]] = piece;
        try { piece.setPosition(position); } catch (IllegalPositionException e) { /* intentional do nothing */ }

        return true;
    }

    public void move(String fromPosition, String toPosition) throws IllegalMoveException {
        try {
            if (Helper.positionIsEmpty(this, fromPosition) || !Helper.isBoundedPosition(toPosition))
                throw new IllegalMoveException();

            ChessPiece piece = getPiece(fromPosition);
            ArrayList<String> moves = piece.legalMoves();

            if (moves.size() == 0 || !moves.contains(toPosition))
                throw new IllegalMoveException();

            int[] boardPositions = convertPositionToBoardIndices(fromPosition);
            int fromRow = boardPositions[0];
            int fromCol = boardPositions[1];

            boardPositions = convertPositionToBoardIndices(toPosition);
            int toRow = boardPositions[0];
            int toCol = boardPositions[1];

            board[toRow][toCol] = board[fromRow][fromCol];
            board[fromRow][fromCol] = null;

            piece.setPosition(toPosition);
        } catch (IllegalPositionException e) {
            throw new IllegalMoveException(); }
    }

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

    //Taken from the assignment specification - for testing purposes only
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.initialize();
        System.out.println(board);
        try{
            board.move("c2", "c4");
        } catch (IllegalMoveException ime){}
        System.out.println(board);
    }

    private int[] convertPositionToBoardIndices(String position) {
        char positionLetter = position.charAt(0);
        char positionNumber = position.charAt(1);

        int row = validNumbers.indexOf(positionNumber);
        int column = validLetters.indexOf(positionLetter);

        return new int[]{row, column};
    }
}
