package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestHelper {
    static void assertExpectedMovesEqualLegalMoves(String[] expectedMovesArray, ArrayList<String> legalMoves) {
        ArrayList<String> expectedMoves = new ArrayList<>(Arrays.asList(expectedMovesArray));
        ArrayList<String> actualMoves = legalMoves;
        expectedMoves.sort(Comparator.naturalOrder());
        actualMoves.sort(Comparator.naturalOrder());

        assertArrayEquals(expectedMoves.toArray(), actualMoves.toArray());
    }
}
