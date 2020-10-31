package cs414f20.teamd.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Hashtable;
import java.sql.ResultSet;

public class Database {
    // connection information when using port forwarding from local host
    private final static String DB_URL = "jdbc:mysql://127.0.0.1:56247/publicclassteamd";
    private final static String DB_USER = "sdonepud";
    private final static String DB_PASSWORD = "831865987";
    // SQL SELECT query statement
    // private final static String COLUMN = "username";
    private final static String QUERY = "SELECT * FROM greatestAccounts;";

    private static void getAllUsers(){
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
                System.out.printf("\t%s\n", results.getString("password"));
            }
        } 
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    private static void setupBoard(Hashtable<String, String> board) {
        String[] cols = {"a","b","c","d","e","f","g","h","i", "j"};
        for(String col : cols) {
            if(col.equals("b") || col.equals("i")) {
                board.put("White rook", col+"0");
                board.put("Black rook", col+"9");
            }
            else if(col.equals("a") || col.equals("j")) {
                board.put("White Knight", col+"0");
                board.put("Black Knight", col+"9");
            }
            else if(col.equals("c") || col.equals("h")) {
                board.put("White Knight", col+"0");
                board.put("Black Knight", col+"9");
            }
            else if(col.equals("d") || col.equals("g")) {
                board.put("White Bishop", col+"0");
                board.put("Black Bishop", col+"9");
            }
            else if(col.equals("e")) {
                board.put("White Queen", col+"0");
                board.put("Black Queen", col+"9");
            }
            else {
                board.put("White King", col+"0");
                board.put("Black King", col+"9");
            }
            board.put("White Pawn", col+"1");
            board.put("Black Pawn", col+"8");
        }
        board.put("Black Wizard", "w3");
        board.put("Black Wizard", "w4");
        board.put("White Wizard", "w1");
        board.put("White Wizard", "w2");
    }

    public static void enterNewGame(int id, String whitePlayer, String blackPlayer) {
        Hashtable<String, String> board = new Hashtable<String, String>();
        setupBoard(board);
        final String q = "INSERT INTO chessGames VALUES("+ id +",\"" + whitePlayer + "\",\""+ blackPlayer+"\",\""
                          + board.toString() + "\",\""+ whitePlayer +"\","+ 0 +");";
        try (
             Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement query = conn.createStatement();
         ) {
            query.executeUpdate(q);
        } 
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // getAllUsers();
        // enterNewGame(20, "me", "not me");
        Hashtable<String, String> board = new Hashtable<String, String>();
        setupBoard(board);
        System.out.println("Size of board: " + board.size());
        System.out.println(board);
    }
}