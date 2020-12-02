package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private ChessPiece[][] board;
    private static final List<Character> validLetters = Helper.validLetters;
    private static final List<Character> validNumbers = Helper.validNumbers;


    ChessBoard() {
        board = new ChessPiece[12][];
        for (int i = 0; i < 10; i++)
            board[i] = new ChessPiece[10];
        board[10] = new ChessPiece[2];
        board[11] = new ChessPiece[2];
    }

    public void initialize(){
        initializePawns();
        initializeCastleFolk();
    }

    private void initializePawns(){
        for (int column = 0; column < 10; column++){
            try {
                int row = 1;
                Pawn pawn = new Pawn(this, ChessPiece.Color.WHITE);
                board[row][column] = pawn;
                pawn.setPosition(Helper.arrayIndicesToPosition(row, column));

                row = 8;
                pawn = new Pawn(this, ChessPiece.Color.BLACK);
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

            int row;
            if (i == 0)
                row = 9;
            else
                row = 0;
            for (int j = 0; j < 8; j++){
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
        try { piece.setPosition(position); } catch (IllegalPositionException e) { return false; }

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

            if (piece instanceof Pawn)
                ((Pawn) piece).justMovedMultipleOffStart();
            else
                for ( int i = 0; i < 10; i++)
                    for (int j = 0; j < 10; j++ )
                        if (getPiece("" + ('a' + i) + ('0' + j) ) instanceof Pawn)
                            ((Pawn) getPiece("" + ('a' + i) + ('0' + j) )).resetJustMovedMultipleOffStart();

        } catch (IllegalPositionException e) {
            throw new IllegalMoveException(); }
    }

    public String toString() {
        String returnString = "";

        ChessPiece w1Piece, w2Piece, w3Piece, w4Piece;
        String w1 = " ", w2 = " ", w3 = " ", w4 = " ";
        w1Piece = board[10][0];
        w2Piece = board[10][1];
        w3Piece = board[11][0];
        w4Piece = board[11][1];
        if (w1Piece != null)
            w1 = w1Piece.toString();
        if (w2Piece != null)
            w2 = w2Piece.toString();
        if (w3Piece != null)
            w3 = w3Piece.toString();
        if (w4Piece != null)
            w4 = w4Piece.toString();

        String topAnnotation = " w4                              w3\n";
        String topRow = "[" + w4 + "]------------------------------[" + w3 + "]\n";

        String middleRows = "";
        for (int row = 9; row >= 0; row--){
            String middleRow = row + "| ";
            for (int col = 0; col < 10; col++) {
                String content = " ";
                if (board[row][col] != null)
                    content = board[row][col].toString();
                middleRow += "[" + content + "]";
            }
            middleRow += " | \n";

            middleRows += middleRow;
        }

        String bottomRow = "[" + w1 + "]-a--b--c--d--e--f--g--h--i--j-[" + w2 + "]\n";
        String bottomAnnotation = " w1                              w2\n";

        returnString = topAnnotation + topRow + middleRows + bottomRow + bottomAnnotation;

        return returnString;
    }

    //Taken from the a2 assignment specification - for testing purposes only
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
        if (position.charAt(0) == 'w') {
            switch (position.charAt(1)) {
                case '1':
                    return new int[]{11, 0};
                case '2':
                    return new int[]{11, 1};
                case '3':
                    return new int[]{10, 0};
                case '4':
                    return new int[]{10, 1};
            }
        }

        char positionLetter = position.charAt(0);
        char positionNumber = position.charAt(1);

        int row = validNumbers.indexOf(positionNumber);
        int column = validLetters.indexOf(positionLetter);

        return new int[]{row, column};
    }
}
