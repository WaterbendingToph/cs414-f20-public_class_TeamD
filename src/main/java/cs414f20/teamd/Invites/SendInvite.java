package cs414f20.teamd.Invites;

import java.util.ArrayList;
import java.util.List;

import cs414f20.teamd.DatabaseConnection.Database;

public class SendInvite {
    public boolean foundPlayer;
    public List<String> failedToSend;
    public List<String> sentInvites;
    public String[] players;
    public String current;

    public SendInvite(String current, String[] players) {
        this.current = current;
        this.players = players;
        this.foundPlayer = false;
        sentInvites = new ArrayList<>();
        failedToSend = new ArrayList<>();
        sendPlayerInvite();
    }

    private void sendPlayerInvite(){
        for(String player : this.players){
            if(!Database.sendInvite(this.current, player) || current.equals(player))
                failedToSend.add(player);
            else{
                sentInvites.add(player);
                this.foundPlayer = true;
            }
        }
    }

    public String getCurrent() {
        return current;
    }

    public String[] getPlayers() {
        return players;
    }

    public List<String> getFailedToSend() {
        return failedToSend;
    }

    public List<String> getSentInvites() {
        return sentInvites;
    }
}
