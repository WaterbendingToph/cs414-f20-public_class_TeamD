package cs414f20.teamd.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
    // connection information when using port forwarding from local host
    private final static String DB_URL = "jdbc:mysql://faure.cs.colostate.edu/publicclassteamd";
    private final static String DB_USER = "sdonepud";
    private final static String DB_PASSWORD = "*";
    // SQL SELECT query statement
    private final static String COLUMN = "username";
    private final static String QUERY = "SELECT * FROM greatestAccounts;";
    public static void main(String[] args) {
         try (
             // connect to the database and query
             Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement query = conn.createStatement();
             ResultSet results = query.executeQuery(QUERY)
         ) {
            // iterate through query results and print out the column values
            int count = 0;
            while (results.next()) {
            System.out.printf("%6d %s\n", ++count, results.getString(COLUMN));
            }
        } catch (Exception e) {
         System.err.println("Exception: " + e.getMessage());
        }
    }
}