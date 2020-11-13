package cs414f20.teamd.Lobby;

public class Lobby {
    private String userID;
    private String password;
    private java.time.LocalDate date;

    public Lobby() {
    }

    public Lobby(String userID, String password) {
        this.userID = userID;
        this.password = password;
        this.date = java.time.LocalDate.now();
    }

    public String getUserID() {
        return this.userID;
    }

    public String getPassword() {
        return this.password;
    }

    public java.time.LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "{" +
            "========== TEST: Lobby Object Sent to Controller==========\n" +
            " userID='" + getUserID() + "'" +
            ", password='" + getPassword() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }

}
