package cs414f20.teamd.Registration;

// import cs414f20.teamd.DatabaseConnection.Database;

public class Registration {
    private String userID;
    private String password;
    private java.time.LocalDate date;
    boolean registrationSuccess;
    String dbResults;

    public Registration(String userID, String password) {
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

    public boolean getRegistrationSuccess() {
        return this.registrationSuccess;
    }

    @Override
    public String toString() {
        return "{" + "========== TEST: Registration Object Sent to Controller==========\n" + " userID='" + getUserID()
                + "'" + ", password='" + getPassword() + "'" + ", date='" + getDate() + "'"
                + ", successfully registered='" + getRegistrationSuccess() + "'" + ", database results='" + dbResults
                + "'" + "}";
    }
    
    void registerUser() {
        // dbResults = Database.tryLogin(this.userID, this.password);
        dbResults = "Dummy - user registered!";
        registrationSuccess = !dbResults.equals("");
    } 
}
