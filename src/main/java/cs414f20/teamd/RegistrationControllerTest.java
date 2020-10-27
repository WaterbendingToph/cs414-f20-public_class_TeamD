package cs414f20.teamd;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationControllerTest {
    RegistrationController registrationController;
    String[] validNameFodder = {"1", "AlbertEinstein", "BobbyJoe", "1234567890123456"};
    String[] invalidNameFodder = {"", "+=][\\-;--;", "12345678901234567"};

    @Test
    public void UsernamesAreSanitized(){
        ArrayList<String> validUsernames = new ArrayList<String>(Arrays.asList(validNameFodder));
        ArrayList<String> invalidUsernames = new ArrayList<String>(Arrays.asList(invalidNameFodder));

        for (String name : validUsernames) {
            boolean valid = RegistrationController.UserIDIsSanitary(name);
            assertTrue(valid);
        }

        for (String name : invalidUsernames) {
            boolean valid = RegistrationController.UserIDIsSanitary(name);
            assertFalse(valid);
        }
    }

    @Test
    public void UserIsRegisteredTest(){
        registrationController = new RegistrationController();

        UsernamesAreSanitized();

        String message = registrationController.UserIsRegistered("AlbertEinstein");
        boolean registered = message.contains("true");
        assertFalse(registered);
        //NOTE: Test incomplete, does not check the actual database.
    }

    @Test
    public void RegisterUserTest(){
        UsernamesAreSanitized();
        //NOTE: Test incomplete, does not register a user in the database.
    }
}
