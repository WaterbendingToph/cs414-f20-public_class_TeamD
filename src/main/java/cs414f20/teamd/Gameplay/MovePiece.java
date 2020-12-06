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
        Helper.trimProperly(dbBoard);
        board.databaseToChessBoard(dbBoard);
        try{
            board.move(from, to);
            Database.setBoardState(gameID, board.chessBoardTodatabase());
            Database.switchWhoseTurn(gameID);

            this.board = returnNewBoard();
        }
        catch(IllegalMoveException e){
            error = "Illegal Move";
        }
    }

    private String returnNewBoard(){
        ArrayList<String> boardState = Database.getBoardState(gameID);
        Helper.trimProperly(boardState);
        JSONObject pieces = new JSONObject();
        for(String square : boardState){
            String[] pieceAndPosition = square.split("=");
            pieces.put(pieceAndPosition[0], pieceAndPosition[1]);
        }
        return pieces.toString();
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
