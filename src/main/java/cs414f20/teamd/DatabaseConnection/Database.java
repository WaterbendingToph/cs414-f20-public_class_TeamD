package cs414f20.teamd.DatabaseConnection;

import static java.sql.DriverManager.getConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;

public class Database {
    // connection information when using port forwarding from local host
    private final static String DB_URL = "jdbc:mysql://127.0.0.1:56247/publicclassteamd";
    private final static String DB_USER = "nic1571";
    private final static String DB_PASSWORD = "password";
    // SQL SELECT query statement
    // private final static String COLUMN = "username";
    private final static String QUERY = "SELECT * FROM greatestAccounts;";

    public static void getAllUsers() {
        try (
                // connect to the database and query
                Connection conn = getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement query = conn.createStatement();
                ResultSet results = query.executeQuery(QUERY)) {
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

    private static void setupBoard(Hashtable<String, String> board) {
        String[] cols = {"a","b","c","d","e","f","g","h","i", "j"};
        for(String col : cols) {
            int count = 0;
            if(col.charAt(0) <= 'e')
                count = 1;
            if(col.equals("b") || col.equals("i")) {
                board.put("White rook"+count, col+"0");
                board.put("Black rook"+count, col+"9");
            }
            else if(col.equals("a") || col.equals("j")) {
                board.put("White Champion"+count, col+"0");
                board.put("Black Champion"+count, col+"9");
            }
            else if(col.equals("c") || col.equals("h")) {
                board.put("White Knight"+count, col+"0");
                board.put("Black Knight"+count, col+"9");
            }
            else if(col.equals("d") || col.equals("g")) {
                board.put("White Bishop"+count, col+"0");
                board.put("Black Bishop"+count, col+"9");
            }
            else if(col.equals("e")) {
                board.put("White Queen"+count, col+"0");
                board.put("Black Queen"+count, col+"9");
            }
            else {
                board.put("White King"+count, col+"0");
                board.put("Black King"+count, col+"9");
            }
            board.put("White Pawn"+col, col+"1");
            board.put("Black Pawn"+col, col+"8");
        }
        board.put("Black Wizard0", "w3");
        board.put("Black Wizard1", "w4");
        board.put("White Wizard0", "w1");
        board.put("White Wizard1", "w2");
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
    
    public static boolean tryLogin(String username, String password) {
        String loginQuery = "SELECT * FROM greatestAccounts WHERE username = '" + username + "';";

        try (
            Connection conn = getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement query = conn.createStatement();
            ResultSet results = query.executeQuery(loginQuery)) {
            while (results.next()) {
                if(BCrypt.checkpw(password, results.getString("password")))
                    return true;
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return false;
    }

    public static int registerUser(String username, String password) {
        String registrationQuery = "INSERT INTO greatestAccounts VALUES (NULL, '" + username + "', '" + password
                + "', '');";
        int dbResult = 0;
        
        Connection conn = null;
        Statement query = null;
        try {
            // connect to the database and query
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            query = conn.createStatement();
            // executeUpdate returns the number of lines in the database that were
            // affected by the query. When registering a user, this should be "1"
            // if registration succeeded or "0" if not.
            dbResult = query.executeUpdate(registrationQuery);

        } catch (Exception e) {
            System.err.println("Error while Registering User: " + e.getMessage());
        } finally {
            closeConnections(conn, query);
        }

        return dbResult;
    }

    public static boolean userExists(String opponent){
        Connection conn = null;
        Statement query = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            query = conn.createStatement();
            String queryStatement = "SELECT * FROM greatestAccounts WHERE username=\""+ opponent +"\";";
            ResultSet results = query.executeQuery(queryStatement);
            while (results.next()) {
                if(results.getString("username").equals(opponent))
                    return true;
            }

        } catch (Exception e) {
            System.err.println("Error while Registering User: " + e.getMessage());
        } finally {
            closeConnections(conn, query);
        }
        return false;
    }

    public static boolean userExists(String[] opponents){
        for(String opponent : opponents){
            if(userExists(opponent))
                return true;
        }
        return false;
    }

    public static boolean sendInvite(String current, String opponent){
        String[] existingInvites = getUserInvites(opponent);
        for(String user: existingInvites){
            if(user.equals(current))
                return false;
        }
        if(userExists(current) && userExists(opponent)){
            Connection conn = null;
            Statement query = null;
            try {
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                query = conn.createStatement();
                String queryStatement = "SELECT * FROM greatestAccounts WHERE username=\""+ opponent +"\";";
                ResultSet results = query.executeQuery(queryStatement);
                String currentPlayers = "";
                while (results.next()) {
                    currentPlayers = results.getString("invites");
                }
                currentPlayers += current + ",";
                queryStatement = "UPDATE greatestAccounts SET invites=\""+ currentPlayers +"\" WHERE username=\""+ opponent +"\";";
                query.executeUpdate(queryStatement);
                return true;
            } catch (Exception e) {
                System.err.println("Error while Sending Invites to User: " + e.getMessage());
            } finally {
                closeConnections(conn, query);
            }
        }
        return false;
    }
    
    // Used for Statement queries (sent to database) that do not automatically
    // close their connections.
    static void closeConnections(Connection conn, Statement query) {
        if ((conn != null) && (query != null)) {
            try {
                conn.close();
                query.close();
            } catch (Exception e) {
                System.err.println("Database Error: " + e.getMessage());
            }
        }
    }

    public static String[] getUserInvites(String current){
        Connection conn = null;
        Statement query = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            query = conn.createStatement();
            String queryStatement = "SELECT * FROM greatestAccounts WHERE username=\""+ current +"\";";
            ResultSet results = query.executeQuery(queryStatement);
            while (results.next()) {
                String invites = results.getString("invites");
                return invites.split(",");
            }
        } catch (Exception e) {
            System.err.println("Error while Getting Invites to User: " + e.getMessage());
        } finally {
            closeConnections(conn, query);
        }
        return null;
    }

    public static boolean deleteInvite(String current, String player){
        Connection conn = null;
        Statement query = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            query = conn.createStatement();
            String queryStatement = "SELECT * FROM greatestAccounts WHERE username=\""+ current +"\";";
            ResultSet results = query.executeQuery(queryStatement);
            String newInvites = "";
            while (results.next()) {
                String invites = results.getString("invites");
                newInvites = invites.replace(player+",", "");
            }
            queryStatement = "UPDATE greatestAccounts SET invites=\""+ newInvites +"\" WHERE username=\""+ current +"\";";
            query.executeUpdate(queryStatement);
            return true;
        } catch (Exception e) {
            System.err.println("Error while Getting Invites to User: " + e.getMessage());
        } finally {
            closeConnections(conn, query);
        }
        return false;
    }

    public static List<String> retrieveUsers(String player){
        List<String> ret = new ArrayList<>();
        Connection conn = null;
        Statement query = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            query = conn.createStatement();
            ResultSet results = query.executeQuery("SELECT * FROM greatestAccounts WHERE username LIKE \""+ player +"%\";");
            while (results.next()) {
                ret.add(results.getString("username"));
            }
        } catch (Exception e) {
            System.err.println("Error while Retrieving User: " + e.getMessage());
        } finally {
            closeConnections(conn, query);
        }
        return ret;
    }

    public static boolean setSearching(String current){
        Connection conn = null;
        Statement query = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            query = conn.createStatement();
            String queryStatement = "UPDATE greatestAccounts SET searching_for_new_game=1 WHERE username=\""+ current +"\";";
            query.executeUpdate(queryStatement);
            return true;
        } catch (Exception e) {
            System.err.println("Error while Setting New Game: " + e.getMessage());
        } finally {
            closeConnections(conn, query);
        }
        return false;
    }

    public static void main(String[] args) {
        // getAllUsers();
        // enterNewGame(20, "me", "not me");
        // Hashtable<String, String> board = new Hashtable<String, String>();
        // setupBoard(board);
        // System.out.println("Size of board: " + board.size());
        // System.out.println(board);
    }
}