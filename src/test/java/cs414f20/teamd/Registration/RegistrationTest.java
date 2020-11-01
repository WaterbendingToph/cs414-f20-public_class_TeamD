package cs414f20.teamd.Registration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RegistrationTest {
    @Test
    void testRegistration() {
        Registration test = new Registration("nick", "42");
        assertEquals("nick", test.getUserID());
        assertEquals("42", test.getPassword());
    }
    
}
