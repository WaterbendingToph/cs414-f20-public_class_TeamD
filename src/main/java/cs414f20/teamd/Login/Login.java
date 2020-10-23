package cs414f20.teamd.Login;

public class Login {
    private String userID;
    private String password;
    private java.time.LocalDate date;


    public Login() {
    }

    public Login(String userID, String password) {
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
            " userID='" + getUserID() + "'" +
            ", password='" + getPassword() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }

}
