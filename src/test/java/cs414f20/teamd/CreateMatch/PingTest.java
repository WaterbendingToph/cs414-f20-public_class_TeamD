package cs414f20.teamd.CreateMatch;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class PingTest {
	final String DB_URL = "jdbc:mysql://127.0.0.1:56247/publicclassteamd";
	final String DB_USER = "sdonepud";
	final String DB_PASSWORD = "831865987";
	
	final String username = "TestAccount";
	final String password = "a";
	
	private void executeUpdate(String statement) {
		try{
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement query = conn.createStatement();
			query.executeUpdate(statement);
		} catch (Exception e) {
			fail("Unexpected error when executing ("+ statement +"): " + e.getMessage());
		}
	}
	
	@AfterEach
	void resetAccountAndGames() {
		String[] queries = {"UPDATE greatestAccounts SET searching_for_new_game=0, invites='' WHERE username='"+ username +"';",
							"DELETE FROM chessGames WHERE white_player='"+ username +"' OR black_player='"+ username +"';"};
		for(String query : queries)
			executeUpdate(query);
	}

	@Test
	void testInitalData() {
		String[] players = {"bob"};
		Ping test = new Ping(username, players, "1980-05-21 00:00:00");
		assertEquals(username, test.getCurrent());
		assertEquals("", test.getGameID());
		assertEquals("1980-05-20 00:00:00.0", test.getDate());
		assertTrue(!test.getIsNewMatchCreated());
		assertEquals(players, test.getPlayers());
	}
	
	@Test
	void testWhenSearchingWithoutNewGame() {
		String[] players = {"bob"};
		executeUpdate("UPDATE greatestAccounts SET searching_for_new_game=1 WHERE username='"+ username +"';");
		Ping test = new Ping(username, players, "1980-05-21 00:00:00");
		assertEquals(username, test.getCurrent());
		assertEquals("", test.getGameID());
		assertEquals("1980-05-20 00:00:00.0", test.getDate());
		assertTrue(!test.getIsNewMatchCreated());
		assertEquals(players, test.getPlayers());
	}
	
	@Test
	void testWhenSearchingWithNewGame() {
		String[] players = {"bob"};
		executeUpdate("UPDATE greatestAccounts SET searching_for_new_game=1 WHERE username='"+ username +"';");
		executeUpdate("INSERT INTO chessGames VALUES(20, 'bob', '"+ username +"', 'test board', 'bob', 0, '1980-05-21 00:01:00');");
		Ping test = new Ping(username, players, "1980-05-21 00:00:00");
		assertEquals(username, test.getCurrent());
		assertEquals("20", test.getGameID());
		assertEquals("1980-05-20 00:00:00.0", test.getDate());
		assertTrue(test.getIsNewMatchCreated());
		assertEquals(players, test.getPlayers());
	}
	
	@Test
	void testWhenSearchingWithOldGame() {
		String[] players = {"bob"};
		executeUpdate("UPDATE greatestAccounts SET searching_for_new_game=1 WHERE username='"+ username +"';");
		executeUpdate("INSERT INTO chessGames VALUES(20, 'bob', '"+ username +"', 'test board', 'bob', 0, '1980-05-19 12:01:00');");
		Ping test = new Ping(username, players, "1980-05-21 00:00:00");
		assertEquals(username, test.getCurrent());
		assertEquals("", test.getGameID());
		assertEquals("1980-05-20 00:00:00.0", test.getDate());
		assertTrue(!test.getIsNewMatchCreated());
		assertEquals(players, test.getPlayers());
	}

}
