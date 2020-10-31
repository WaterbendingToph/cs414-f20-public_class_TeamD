package cs414f20.teamd.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
    // connection information when using port forwarding from local host
    private final static String DB_URL = "jdbc:mysql://127.0.0.1:56247/publicclassteamd";
    private final static String DB_USER = "sdonepud";
    private final static String DB_PASSWORD = "831865987";
    // SQL SELECT query statement
    // private final static String COLUMN = "username";
    private final static String QUERY = "SELECT * FROM greatestAccounts;";

    public static void getAllUsers() {
        try (
                // connect to the database and query
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement query = conn.createStatement();
                ResultSet results = query.executeQuery(QUERY)
        ) {
            // iterate through query results and print out the column values
            int count = 0;
            while (results.next()) {
                System.out.printf("%6d %s", ++count, results.getString("personalID"));
                System.out.printf("\t%s", results.getString("username"));
                System.out.printf("\t%s%n", results.getString("password"));
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public static String tryLogin(String username, String password) {
        String loginQuery = "SELECT username FROM greatestAccounts WHERE username = '" + username + "' AND password = '"
                + password + "';";
        String userReturned = "";

        try (
                // connect to the database and query
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement query = conn.createStatement();
                ResultSet results = query.executeQuery(loginQuery)) {
            while (results.next()) {
                userReturned = results.getString("username");
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return userReturned;
    }
    
    public static int registerUser(String username, String password) {
        String registrationQuery = "INSERT INTO greatestAccounts VALUES (NULL, '" + username + "', '" + password
                + "');";
        int dbResult;

        try (
                // connect to the database and query
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement query = conn.createStatement();
                int result = query.executeUpdate(registrationQuery)) {

// FIXME: issue with returning the integer from the executeUpdate and you apparently have to close the connections somehow.

            dbResult = result;
            query.close();
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
        

        return dbResult;
    }

    public static void main(String[] args) {
        getAllUsers();
    }
}