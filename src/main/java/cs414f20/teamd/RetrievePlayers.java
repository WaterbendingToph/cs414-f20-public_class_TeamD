package cs414f20.teamd;

import java.util.ArrayList;
import java.util.List;

import cs414f20.teamd.DatabaseConnection.Database;

public class RetrievePlayers {
    public String player;
    public List<String> playersInDB;

    public RetrievePlayers(String player) {
        this.player = player;
        playersInDB = new ArrayList<>();
        retrievePlayers();
    }

    public String getPlayer() {
        return player;
    }

    public List<String> getPlayersInDB() {
        return playersInDB;
    }

    private void retrievePlayers(){
        playersInDB = Database.retrieveUsers(this.player);
    }
}
