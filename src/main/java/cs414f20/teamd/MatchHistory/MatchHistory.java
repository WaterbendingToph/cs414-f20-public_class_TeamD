package cs414f20.teamd.MatchHistory;

import cs414f20.teamd.DatabaseConnection.Database;

import java.util.List;

// import javax.xml.crypto.Data;

public class MatchHistory {
    private String userID;
    private String password;
    private List<List<String>> history;

    public MatchHistory() { 
    }

    public MatchHistory(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean hasHistory() {
        return !this.history.isEmpty();
    }

    public List<List<String>> getHistory() {
        return this.history;
    }

    public boolean attemptLogin() {
        return Database.tryLogin(this.userID, this.password);
    }

    @Override
    public String toString() {
        return "{" +
            "========== TEST: Ongoing Matches Object Sent to Controller==========\n" +
            " userID='" + getUserID() + "'" +
            " password='" + getPassword() + "'" +
            " logged in ='" + attemptLogin() + "'" +
            ", has ongoing matches='" + hasHistory() + "'" +
            ", match list='" + getHistory() + "'" +
            "}";
    }

    public void queryDatabase() {
        if (attemptLogin()) {
            this.history = Database.getMatchHistory(this.userID);
        }
    }

}
