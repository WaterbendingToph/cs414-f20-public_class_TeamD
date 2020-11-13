package cs414f20.teamd.CreateMatch;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CreateMatchTest {
    @Test
    void testCreateMatch() {
        LocalDate date = java.time.LocalDate.now();
        String[] testPlayers = {"Steve", "Bob"};
        CreateMatch cm = new CreateMatch(1, "Jimmy", testPlayers);
        assertEquals(date, cm.getLocalDate());
        assertEquals(1, cm.getGameID());
        assertEquals(testPlayers, cm.getOpponnet());
    }
}
