package cs414f20.teamd.Invites;

import cs414f20.teamd.DatabaseConnection.Database;

public class SendInvite {
    public boolean foundPlayer;
    public String player;
    public String current;

    public SendInvite(String current, String player) {
        this.current = current;
        this.player = player;
        foundPlayer = sendPlayerInvite();
    }

    private boolean sendPlayerInvite(){
        return Database.sendInvite(this.current, this.player);
    }

    public String getCurrent() {
        return current;
    }

    public String getPlayer() {
        return player;
    }

    public boolean getFoundPlayer(){
        return foundPlayer;
    }
}
