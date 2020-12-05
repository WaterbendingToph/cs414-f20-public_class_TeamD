package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONObject;

import cs414f20.teamd.DatabaseConnection.Database;

public class MovePiece {
    private String gameID;
    private String from;
    private String to;
    private String board;
    private String error;

    public MovePiece(String gameID, String from, String to) {
        this.gameID = gameID;
        this.from = from;
        this.to = to;
        this.board = Database.getBoardState(gameID).toString();
        this.error = "";
        movePiece();
    }

    public void movePiece(){
        ArrayList<String> dbBoard = Database.getBoardState(gameID);
        ChessBoard board = new ChessBoard();
        trimProperly(dbBoard);
        board.databaseToChessBoard(dbBoard);
        try{
            board.move(from, to);
            System.out.println(board);
            System.out.println(board.chessBoardTodatabase());
            Database.setBoardState(gameID, board.chessBoardTodatabase());
            Database.switchWhoseTurn(gameID);
            this.board = board.chessBoardTodatabase();
        }
        catch(IllegalMoveException e){
            error = "Illegal Move";
        }
    }

    private static void trimProperly(ArrayList<String> board) {
        String replacement = board.get(0).substring(1, board.get(0).length() - 1);
        String[] temp = replacement.split(",");
        for(int i = 0; i<temp.length; i++)
            temp[i] = temp[i].trim();
        board.clear();
        Collections.addAll(board, temp);
    }

    public String getFrom() {
        return from;
    }

    public String getGameID() {
        return gameID;
    }

    public String getTo() {
        return to;
    }

    public String getBoard() {
        return board;
    }

    public String getError() {
        return error;
    }
}
