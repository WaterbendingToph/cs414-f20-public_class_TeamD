package cs414f20.teamd.DatabaseConnection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.junit.jupiter.api.Test;

class DatabaseTest {
	final String DB_URL = "jdbc:mysql://127.0.0.1:56247/publicclassteamd";
	final String DB_USER = "sdonepud";
	final String DB_PASSWORD = "831865987";

	@Test
	void testDatabaseAccountsSetup() {
		// final String DB_URL = "jdbc:mysql://127.0.0.1:56247/publicclassteamd";
		// final String DB_USER = "sdonepud";
		// final String DB_PASSWORD = "831865987";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement query = conn.createStatement();
				ResultSet results = query.executeQuery("SELECT * FROM greatestAccounts;");) {
			while (results.next()) {
				int id = Integer.parseInt(results.getString("personalID"));
				String user = results.getString("username");
				String password = results.getString("password");
				assertNotNull(id);
				assertNotNull(user);
				assertNotNull(password);
			}
		} catch (Exception e) {
			fail("Unexpected error: " + e.getMessage());
		}
	}
	
	@Test
	void testDatabaseLoginQuery() {
		try (
	         Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	         Statement query = conn.createStatement();
	         ResultSet results = query.executeQuery("SELECT username FROM greatestAccounts WHERE username = 'nick' AND password = '42';");
	    ) {
	        while (results.next()) {
				String userReturned = results.getString("username");
				assertEquals("nick", userReturned);
	        }
	    } 
	    catch (Exception e) {
	        fail("Unexpected error: " + e.getMessage());
	    }
	}
	
	@Test
	void testCreateMatchQuery() {
		Random r = new Random();
		int id = r.nextInt();
		Database.enterNewGame(id, "stevie", "bobby");
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement query = conn.createStatement();
				ResultSet results = query.executeQuery("SELECT * FROM chessGames WHERE gameID="+ id +";");) {
			while (results.next()) {
				int gameID = Integer.parseInt(results.getString("gameID"));
				String white = results.getString("white_player");
				String black = results.getString("black_player");
				String currentP = results.getString("whose_turn");
				String completed = results.getString("completed");
				assertEquals(gameID, id);
				assertEquals(white, "stevie");
				assertEquals(black, "bobby");
				assertEquals(currentP, "stevie");
				assertEquals(completed, "0");
				assertNotNull(results.getString("board"));
			}
		} catch (Exception e) {
			fail("Unexpected error when CREATING MATCH: " + e.getMessage());
		}
		finally {
			try {
				Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement query = conn.createStatement();
				String delete = "DELETE FROM chessGames WHERE white_player=\"stevie\";";
				query.executeUpdate(delete);
			} catch (SQLException e) {
				fail("FAILED TO DELETE: " + e.getMessage());
			}
		}
		
	}
}
