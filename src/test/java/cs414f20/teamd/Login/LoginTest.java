package cs414f20.teamd.Login;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @Test
    void testLogin() {
        Login test = new Login("Nick", "password");
        assertEquals("Nick", test.getUserID());
        assertEquals("password", test.getPassword());
        assertTrue(test.loggedIn());
    }
}
