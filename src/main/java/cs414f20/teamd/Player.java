package cs414f20.teamd;

public class Player {
    private String userID;

    public Player(String id, String password) {
        userID = id;
    }

    public String getUserID() {
        return userID;
    }
}
