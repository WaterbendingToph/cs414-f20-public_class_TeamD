package cs414f20.teamd.CreateMatch;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import cs414f20.teamd.DatabaseConnection.Database;

class SearchingTest {
	static final String DB_URL = "jdbc:mysql://127.0.0.1:56247/publicclassteamd";
	static final String DB_USER = "sdonepud";
	static final String DB_PASSWORD = "831865987";
	
	static final String username = "TestAccount";
	static final String password = "searching";
	
	private static void executeUpdate(String statement) {
		try{
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement query = conn.createStatement();
			query.executeUpdate(statement);
		} catch (Exception e) {
			fail("Unexpected error when executing ("+ statement +"): " + e.getMessage());
		}
	}
	
	@BeforeAll
	static void setUpTestAccount() {
		String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        Database.registerUser(username, hash);
	}
	
	@AfterEach
	void resetAccountAndGames() {
		String[] queries = {"UPDATE greatestAccounts SET searching_for_new_game=0, invites='' WHERE username='"+ username +"';",
							"DELETE FROM chessGames WHERE white_player='"+ username +"' OR black_player='"+ username +"';"};
		for(String query : queries)
			executeUpdate(query);
	}
	
	@AfterAll
	static void deleteTestUser() {
		executeUpdate("DELETE FROM greatestAccounts WHERE username='"+username+"';");
	}

	@Test
	void testSetSearching() {
		Searching test = new Searching(username);
		assertEquals(username, test.getCurrent());
		assertTrue(test.getSearching());
	}

}
