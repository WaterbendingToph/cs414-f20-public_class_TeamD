package cs414f20.teamd.OngoingMatches;

import cs414f20.teamd.DatabaseConnection.Database;

import java.util.List;

public class OngoingMatches {
    private String userID;
    private List<List<String>> matches;

    public OngoingMatches() {
    }

    public OngoingMatches(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return this.userID;
    }

    public boolean hasMatches() {
        return this.matches != null;
    }

    public List<List<String>> getMatches() {
        return this.matches;
    }

    @Override
    public String toString() {
        return "{" +
            "========== TEST: Ongoing Matches Object Sent to Controller==========\n" +
            " userID='" + getUserID() + "'" +
            ", has ongoing matches='" + hasMatches() + "'" +
            ", match list='" + getMatches() + "'" +
            "}";
    }

    void queryDatabase() {
        this.matches = Database.getOngoingMatches(userID);
    } 

    
}
