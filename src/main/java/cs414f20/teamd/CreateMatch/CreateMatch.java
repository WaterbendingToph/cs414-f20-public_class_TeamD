package cs414f20.teamd.CreateMatch;

import java.time.LocalDate;

public class CreateMatch {
    private long gameID;
    private String opponent;
    private java.time.LocalDate date;

    public CreateMatch(long gameID, String opponent) {
        this.gameID = gameID;
        this.opponent = opponent;
        this.date = java.time.LocalDate.now();
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
}
