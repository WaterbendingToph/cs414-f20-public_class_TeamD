package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    private Bishop bishop;
    private Bishop secondBishop;

    @Test
    void testToString() {
        ChessBoard requisiteBoard = new ChessBoard();
        bishop = new Bishop(requisiteBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2657", bishop.toString());

        secondBishop = new Bishop(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265D", secondBishop.toString());

        assertNotEquals(bishop.toString(), secondBishop.toString());
    }

    @Test
    void legalMoves() {
        ChessBoard requisiteBoard = new ChessBoard();
        bishop = new Bishop(requisiteBoard, ChessPiece.Color.BLACK);

        try {
            bishop.setPosition("b2");
        } catch (IllegalPositionException ime) {
            System.out.println("Oops");
        }

        String[] tofu = {"a1", "c3", "d4", "e5", "f6", "g7", "h8", "a3", "c1"};
        ArrayList<String> expectedValidMoves = new ArrayList<String>(Arrays.asList(tofu));
        ArrayList<String> actualValidMoves = bishop.legalMoves();

        expectedValidMoves.sort(Comparator.naturalOrder());
        actualValidMoves.sort(Comparator.naturalOrder());

        assertArrayEquals(expectedValidMoves.toArray(), actualValidMoves.toArray());
    }//NOTE: There is opportunity for a test here to account for the bishop having its path blocked by friend or foe, like the RookTest.
}