package cs414f20.teamd.Registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

import cs414f20.teamd.DatabaseConnection.Database;

// import cs414f20.teamd.DatabaseConnection.Database;

public class Registration {
    private String userID;
    private String password;
    private java.time.LocalDate date;
    boolean registrationSuccess;
    int dbResults = 0;

    static String validIDRegex="^[a-z,A-Z,0-9]{1,16}$";

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
        return "\n{" + "========== TEST: Registration Object Sent to Controller==========\n" + " userID='" + getUserID()
                + "'" + ", password='" + getPassword() + "'" + ", date='" + getDate() + "'"
                + ", successfully registered='" + getRegistrationSuccess() + "'" + ", database results='" + Integer.toString(dbResults)
                + "'" + "}\n";
    }

    boolean userIDIsSanitary() {
        Pattern pattern = Pattern.compile(validIDRegex);
        Matcher matcher = pattern.matcher(this.userID);
        return matcher.matches();
    }

    boolean passwordIsSanitary() {
        Pattern pattern = Pattern.compile(validIDRegex);
        Matcher matcher = pattern.matcher(this.password);
        return matcher.matches();
    }
    
    void registerUser() {
        if (userIDIsSanitary() && passwordIsSanitary()) {
            String hash = BCrypt.hashpw(getPassword(), BCrypt.gensalt());
            dbResults = Database.registerUser(getUserID(), hash);
        } else {
            System.out.println("Invalid user ID! Please try again.");
        }
        registrationSuccess = (dbResults == 1);
    }
}
