package cs414f20.teamd.CreateMatch;

import java.time.LocalDate;

import cs414f20.teamd.DatabaseConnection.Database;

public class CreateMatch {
    private int gameID;
    private String[] opponents;
    private java.time.LocalDate date;
    private boolean started;

    public CreateMatch(int gameID, String[] opponents) {
        this.gameID = gameID;
        this.opponents = opponents;
        this.date = java.time.LocalDate.now();
        this.started = findPlayers(opponents);
        if(this.started)
            enterNewGameToDB();
    }

    private boolean findPlayers(String[] opponents){
        return Database.userExists(opponents);
    }

    private void enterNewGameToDB(){
        Database.enterNewGame(gameID, "currentPlayer", opponents[0]);
    }

    public long getGameID() {
        return gameID;
    }

    public String[] getOpponnet(){
        return opponents;
    }

    public java.time.LocalDate getLocalDate(){
        return date;
    }

    public boolean getGameStarted(){
        return started;
    }
}
