package cs414f20.teamd.DatabaseConnection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class DatabaseTest {

	@Test
	void testDatabaseAccountsSetup() {
		final String DB_URL = "jdbc:mysql://127.0.0.1:56247/publicclassteamd";
	    final String DB_USER = "sdonepud";
	    final String DB_PASSWORD = "831865987";
		try (
	         Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	         Statement query = conn.createStatement();
	         ResultSet results = query.executeQuery("SELECT * FROM greatestAccounts;");
	    ) {
	        while (results.next()) {
	            int id = Integer.parseInt(results.getString("personalID"));
	            String user = results.getString("username");
	            String password = results.getString("password");
	            assertNotNull(id);
	            assertNotNull(user);
	            assertNotNull(password);
	        }
	    } 
	    catch (Exception e) {
	        fail("Unexpected error: " + e.getMessage());
	    }
	}
}
