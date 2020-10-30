package cs414f20.teamd;

public class Player {
    private String userID;
    private String password;

    public Player(String id, String password) {
        userID = id;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }
}
