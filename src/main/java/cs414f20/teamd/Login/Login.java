package cs414f20.teamd.Login;

import cs414f20.teamd.DatabaseConnection.Database;

public class Login {
    private String userID;
    private String password;
    private java.time.LocalDate date;
    boolean loginSuccess;
    // private Database db;

    public Login() {
    }

    public Login(String userID, String password) {
        this.userID = userID;
        this.password = password;
        this.date = java.time.LocalDate.now();
        // this.db = new Database();
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
            "}";
    }

    String queryDatabase() {
        String dbResults = "TEST: User is logged in.";
        // ------------------------------TODO: ADD DATABASE QUERY HERE------------------------------ //
        // String dbResults = db.getAllUsers(); <- Uses existing Database.java file
        return dbResults;
    } 

    boolean loggedIn() {
        loginSuccess = queryDatabase() != null;
        return loginSuccess;
    }
}

