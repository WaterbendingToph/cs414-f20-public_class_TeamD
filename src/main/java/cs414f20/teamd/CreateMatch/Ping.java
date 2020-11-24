package cs414f20.teamd.CreateMatch;

import cs414f20.teamd.DatabaseConnection.Database;

public class Ping {
    private String current;
    private String gameID;
    private String[] players;
    private boolean isNewMatchCreated;

    public Ping(String current, String[] players) {
        this.current = current;
        this.players = players;
        this.gameID = "";
        this.isNewMatchCreated = pingForNewMatch();
    }

    private boolean pingForNewMatch(){
        String gameID = Database.pingNewMatch(current, players);
        if(gameID.length() != 0){
            this.gameID = gameID;
            return true;
        }
        return false;
    }

    public String getCurrent() {
        return current;
    }

    public String[] getPlayers() {
        return players;
    }

    public boolean getIsNewMatchCreated(){
        return isNewMatchCreated;
    }

    public String getGameID() {
        return gameID;
    }
}
