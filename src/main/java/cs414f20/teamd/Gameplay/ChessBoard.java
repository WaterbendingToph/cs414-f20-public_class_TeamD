package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import cs414f20.teamd.Gameplay.ChessPiece.Color;

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
        initializeWizards();
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
            pieces.add(new Champion(this, color));
            pieces.add(new Rook(this, color));
            pieces.add(new Knight(this, color));
            pieces.add(new Bishop(this, color));
            pieces.add(new Queen(this, color));
            pieces.add(new King(this, color));
            pieces.add(new Bishop(this, color));
            pieces.add(new Knight(this, color));
            pieces.add(new Rook(this, color));
            pieces.add(new Champion(this, color));

            int row;
            if (i == 0)
                row = 9;
            else
                row = 0;
            for (int j = 0; j < 10; j++){
                ChessPiece piece = pieces.get(j);

                try {
                    board[row][j] = piece;
                    piece.setPosition(Helper.arrayIndicesToPosition(row, j));
                } catch (IllegalPositionException ipe) { /* intentional do nothing, this will not be hit */ }
            }

            pieces.removeAll(pieces);
            color = ChessPiece.Color.WHITE;
        }
    }

    private void initializeWizards() {
        Wizard white1 = new Wizard(this, ChessPiece.Color.WHITE);
        Wizard white2 = new Wizard(this, ChessPiece.Color.WHITE);
        Wizard black1 = new Wizard(this, ChessPiece.Color.BLACK);
        Wizard black2 = new Wizard(this, ChessPiece.Color.BLACK);
        board[10][0] = black1;
        board[10][1] = black2;
        board[11][0] = white1;
        board[11][1] = white2;

        try {
            white1.setPosition("w1");
            white2.setPosition("w2");
            black1.setPosition("w3");
            black2.setPosition("w4");
        } catch (IllegalPositionException ipe) { /* intentional do nothing, this will not be hit */ }

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

    public void databaseToChessBoard(ArrayList<String> board){
        // TODO change database board to actual chessboard
        System.out.println(board.toString());
        for(String piece : board){
            piece = piece.toLowerCase();
            ChessPiece newPiece = null;
            ChessPiece.Color temp = ChessPiece.Color.WHITE;
            String[] colorAndPiece = piece.split(" ");
            if(colorAndPiece[0].equals("black"))
                temp = ChessPiece.Color.BLACK;
            String temp_piece = colorAndPiece[1].split("=")[0];
            System.out.println(temp_piece.substring(0, temp_piece.length()-1));
            switch(temp_piece.substring(0, temp_piece.length()-1)){
                case "bishop":
                    newPiece = new Bishop(this, temp);
                    break;
                case "champion":
                    newPiece = new Champion(this, temp);
                    break;
                case "rook":
                    newPiece = new Rook(this, temp);
                    break;
                case "knight":
                    newPiece = new Knight(this, temp);
                    break;
                case "king":
                    newPiece = new King(this, temp);
                    break;
                case "queen":
                    newPiece = new Queen(this, temp);
                    break;
                case "wizard":
                    newPiece = new Wizard(this, temp);
                    break;
                default:
                    newPiece = new Pawn(this, temp);
                    break;
            }
            placePiece(newPiece, piece.split("=")[1]);
        }
    }

    private String whatPiece(ChessPiece piece){
        if(piece instanceof Rook){
           return "Rook";
        }
        else if(piece instanceof Queen){
            return "Queen";
        }
        else if(piece instanceof Wizard){
            return "Wizard";
        }
        else if(piece instanceof Champion){
            return "Champion";
        }
        else if(piece instanceof Knight){
            return "Knight";
        }
        else if(piece instanceof Bishop){
            return "Bishop";
        }
        else if(piece instanceof King){
            return "King";
        }
        else if(piece == null)
            return "Null piece";
        else {
            return "Pawn";
        }
    }

    public String chessBoardTodatabase(){
        // TODO change actual chessboard to database board (Convert to HashTable<String, String> then call toString on that)
        Hashtable<String, String> newBoard = new Hashtable<>();
        // System.out.println("[BOARD INFO]: # row: " + board.length);
        for(int i = 0; i<board.length; i++){
            // System.out.println("[BOARD INFO]: # columns in row: " + board[i].length);
            for(int j = 0; j < board[i].length; j++){
                ChessPiece piece = board[i][j];
                System.out.println("");
                // System.out.println("[DEBUG]: Current piece: " + whatPiece(piece));
                if(piece != null){
                    String key = "White ";
                    if(piece.getColor() == Color.BLACK)
                        key = "Black ";
                    key += whatPiece(piece);
                    if(piece instanceof Pawn){
                        String[] columns = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
                        for(String col : columns){
                            if(!newBoard.containsKey(key+col)){
                                newBoard.put(key+col, piece.getPosition());
                                break;
                            }
                        }
                    }
                    else{
                        // System.out.println("[DEBUG]: Current key: " + key);
                        if(newBoard.containsKey(key+"0")){
                            newBoard.put(key+"1", piece.getPosition());
                            // System.out.println("Added second piece: " + key + "1");
                        }
                        else{
                            newBoard.put(key+"0", piece.getPosition());
                            // System.out.println("Added first piece: " + key + "0");
                        }
                    }
                }
            }
        }
        // System.out.println("[DEBUG]: Size of the hashtable: " + newBoard.size());
        return newBoard.toString();
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

    public void populateBoard(ArrayList<String> boardState) {
        ChessPiece.Color White = ChessPiece.Color.WHITE;
        ChessPiece.Color Black = ChessPiece.Color.BLACK;
        try {
            for (String thisPiece : boardState) {
                String position = thisPiece.substring(thisPiece.length() - 2);
                if (thisPiece.contains("Black Pawn")) {
                    Pawn pawn = new Pawn(this, Black);
                    placePiece(pawn, position);
                    pawn.setPosition(position);
                }

                if (thisPiece.contains("Black rook")) {
                    Rook rook = new Rook(this, Black);
                    placePiece(rook, position);
                    rook.setPosition(position);
                }

                if (thisPiece.contains("Black Knight")) {
                    Knight knight = new Knight(this, Black);
                    placePiece(knight, position);
                    knight.setPosition(position);
                }

                if (thisPiece.contains("Black Bishop")) {
                    Bishop bishop = new Bishop(this, Black);
                    placePiece(bishop, position);
                    bishop.setPosition(position);
                }

                if (thisPiece.contains("Black Queen")) {
                    Queen queen = new Queen(this, Black);
                    placePiece(queen, position);
                    queen.setPosition(position);
                }

                if (thisPiece.contains("Black King")) {
                    King king = new King(this, Black);
                    placePiece(king, position);
                    king.setPosition(position);
                }

                if (thisPiece.contains("Black Champion")) {
                    Champion champion = new Champion(this, Black);
                    placePiece(champion, position);
                    champion.setPosition(position);
                }

                if (thisPiece.contains("Black Wizard")) {
                    Wizard wizard = new Wizard(this, Black);
                    placePiece(wizard, position);
                    wizard.setPosition(position);
                }


                if (thisPiece.contains("White Pawn")) {
                    Pawn pawn = new Pawn(this, White);
                    placePiece(pawn, position);
                    pawn.setPosition(position);
                }

                if (thisPiece.contains("White rook")) {
                    Rook rook = new Rook(this, White);
                    placePiece(rook, position);
                    rook.setPosition(position);
                }

                if (thisPiece.contains("White Knight")) {
                    Knight knight = new Knight(this, White);
                    placePiece(knight, position);
                    knight.setPosition(position);
                }

                if (thisPiece.contains("White Bishop")) {
                    Bishop bishop = new Bishop(this, White);
                    placePiece(bishop, position);
                    bishop.setPosition(position);
                }

                if (thisPiece.contains("White Queen")) {
                    Queen queen = new Queen(this, White);
                    placePiece(queen, position);
                    queen.setPosition(position);
                }

                if (thisPiece.contains("White King")) {
                    King king = new King(this, White);
                    placePiece(king, position);
                    king.setPosition(position);
                }

                if (thisPiece.contains("White Champion")) {
                    Champion champion = new Champion(this, White);
                    placePiece(champion, position);
                    champion.setPosition(position);
                }

                if (thisPiece.contains("White Wizard")) {
                    Wizard wizard = new Wizard(this, White);
                    placePiece(wizard, position);
                    wizard.setPosition(position);
                }
            }
        } catch (IllegalPositionException ipe) { System.err.println("Error caught while trying to place pieces on board in populateBoard() call of GameplayController's getBoardState " + boardState.get(0).substring(boardState.get(0).length() - 2) ); }
    }
}
