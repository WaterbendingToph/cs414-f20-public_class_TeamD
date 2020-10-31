package cs414f20.teamd.Login;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @Test
    void testLogin() {
        Login test = new Login("nick", "42");
        assertEquals("nick", test.getUserID());
        assertEquals("42", test.getPassword());
    }
}
