package cs414f20.teamd.Login;

import cs414f20.teamd.DatabaseConnection.Database;

public class Login {
    private String userID;
    private String password;
    private java.time.LocalDate date;
    boolean loginSuccess;
    String dbResults;

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

    public boolean getLoginSuccess() {
        return this.loginSuccess;
    }

    @Override
    public String toString() {
        return "{" +
            "========== TEST: Login Object Sent to Controller==========\n" +
            " userID='" + getUserID() + "'" +
            ", password='" + getPassword() + "'" +
            ", date='" + getDate() + "'" +
            ", successfully logged in='" + getLoginSuccess() + "'" +
            ", database results='" + dbResults + "'" +
            "}";
    }

    void attemptLogin() {
        loginSuccess = Database.tryLogin(this.userID, this.password);
    } 

}

