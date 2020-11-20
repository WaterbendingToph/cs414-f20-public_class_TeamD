package cs414f20.teamd.Lobby;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LobbyTest {
    @Test
    void testLobby() {
        Lobby test = new Lobby("nick", "42");
        assertEquals("nick", test.getUserID());
        assertEquals("42", test.getPassword());
        test.attemptLogin();
        assertTrue(test.loginSuccess);
    }
}
