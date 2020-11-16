package cs414f20.teamd.Invites;

import cs414f20.teamd.DatabaseConnection.Database;

public class GetInvites {
    public String currentPlayer;
    public String[] invitesFromPlayers;

    public GetInvites(String currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.invitesFromPlayers = retrieveInvites();
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String[] retrieveInvites(){
        return Database.getUserInvites(this.currentPlayer);
    }
}
