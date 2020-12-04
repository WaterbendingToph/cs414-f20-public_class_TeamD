package cs414f20.teamd.Invites;

import com.mysql.jdbc.DatabaseMetaData;

import cs414f20.teamd.DatabaseConnection.Database;

public class AcceptInvite {
    private String current;
    private String player;
    private boolean accepted;

    public AcceptInvite(String current, String player) {
        this.current = current;
        this.player = player;
        this.accepted = acceptInvite();
    }

    private boolean acceptInvite(){
        return Database.acceptInvite(current, player);
    }

    public String getCurrent() {
        return current;
    }

    public String getPlayer() {
        return player;
    }

    public boolean getAccepted(){
        return accepted;
    }
}
