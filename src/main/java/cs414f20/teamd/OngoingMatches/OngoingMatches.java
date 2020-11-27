package cs414f20.teamd.OngoingMatches;

import cs414f20.teamd.DatabaseConnection.Database;

import java.util.ArrayList;
import java.util.List;

// import javax.xml.crypto.Data;

public class OngoingMatches {
    private String userID;
    private String password;
    private List<List<String>> matches;

    public OngoingMatches() { 
    }

    public OngoingMatches(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean hasMatches() {
        return !this.matches.isEmpty();
    }

    public List<List<String>> getMatches() {
        return this.matches;
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
            ", has ongoing matches='" + hasMatches() + "'" +
            ", match list='" + getMatches() + "'" +
            "}";
    }

    public void queryDatabase() {
        if (attemptLogin()) {
            this.matches = Database.getOngoingMatches(this.userID);
            // if (this.matches.isEmpty()) {
            //     List<String> emptyMatch = new ArrayList<>();
            //     emptyMatch.add("None");
            //     emptyMatch.add("");
            //     emptyMatch.add("");
            //     this.matches.add(emptyMatch);
            // }
        }
    }

}
