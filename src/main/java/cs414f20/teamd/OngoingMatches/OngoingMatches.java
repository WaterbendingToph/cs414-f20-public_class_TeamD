package cs414f20.teamd.OngoingMatches;

// import cs414f20.teamd.DatabaseConnection.Database;

public class OngoingMatches {
    private String userID;
    // private Database db;
    private String matches;

    public OngoingMatches() {
    }

    public OngoingMatches(String userID) {
        this.userID = userID;
        // this.db = new Database();
    }

    public String getUserID() {
        return this.userID;
    }

    public boolean hasMatches() {
        return this.matches != null;
    }

    public String getMatches() {
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
        this.matches = "TEST: User has ongoing matches";
        // ------------------------------TODO: ADD DATABASE QUERY HERE------------------------------ //
        // String dbResults = db.getAllUsers(); <- Uses existing Database.java file
    } 

    
}
