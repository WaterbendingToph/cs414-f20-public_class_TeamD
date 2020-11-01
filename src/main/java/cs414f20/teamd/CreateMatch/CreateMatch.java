package cs414f20.teamd.CreateMatch;

import java.time.LocalDate;

import cs414f20.teamd.DatabaseConnection.Database;

public class CreateMatch {
    private int gameID;
    private String opponent;
    private java.time.LocalDate date;
    private boolean started;

    public CreateMatch(int gameID, String opponent) {
        this.gameID = gameID;
        this.opponent = opponent;
        this.date = java.time.LocalDate.now();
        this.started = true;
        enterNewGameToDB();
    }

    private void enterNewGameToDB(){
        Database.enterNewGame(gameID, "currentPlayer", opponent);
    }

    public long getGameID() {
        return gameID;
    }

    public String getOpponnet(){
        return opponent;
    }

    public java.time.LocalDate getLocalDate(){
        return date;
    }

    public boolean getGameStarted(){
        return started;
    }
}
