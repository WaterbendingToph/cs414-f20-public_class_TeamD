package cs414f20.teamd.DatabaseConnection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement query = conn.createStatement();
				ResultSet results = query.executeQuery(
						"SELECT username FROM greatestAccounts WHERE username = 'nick' AND password = '42';");) {
			while (results.next()) {
				String userReturned = results.getString("username");
				assertEquals("nick", userReturned);
			}
		} catch (Exception e) {
			fail("Unexpected error: " + e.getMessage());
		}
	}
	
	@Test
	void testRegisterUserQuery() {
		String invalidRegistrationQuery = "INSERT INTO greatestAccounts VALUES (NULL, 'nick', 'duplicate should not work');";
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
            dbResult = query.executeUpdate(invalidRegistrationQuery);

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
		} finally {
			Database.closeConnections(conn, query);
		}
		assertEquals(0, dbResult);
	}
}
