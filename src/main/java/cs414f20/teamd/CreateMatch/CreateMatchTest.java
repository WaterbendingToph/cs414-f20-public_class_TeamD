package cs414f20.teamd.CreateMatch;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CreateMatchTest {
    @Test
    void testCreateMatch() {
        LocalDate date = java.time.LocalDate.now();
        CreateMatch cm = new CreateMatch(1, "Steve");
        assertEquals(date, cm.getLocalDate());
        assertEquals(1, cm.getGameID());
        assertEquals("Steve", cm.getOpponnet());
    }
}
