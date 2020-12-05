package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;

class KingTest {
    ChessBoard requisiteBoard;

    @Test
    void testToString() {
        requisiteBoard = new ChessBoard();

        King king = new King(requisiteBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2654", king.toString());

        king = new King(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265A", king.toString());
    }

    @Test
    void legalMoves() {
        testFreeMovement();
        testCornerMovement();
        testSharedSpaceMovement();
    }
    private void testFreeMovement() {
        requisiteBoard = new ChessBoard();
        King king = new King(requisiteBoard, ChessPiece.Color.WHITE);
        requisiteBoard.placePiece(king, "b2");

        ArrayList<String> expectedMoves = new ArrayList<String>(Arrays.asList(new String[]{"a1", "a2", "a3", "b1", "b3", "c1", "c2", "c3"}));
        ArrayList<String> actualMoves = king.legalMoves();
        expectedMoves.sort(Comparator.naturalOrder());
        actualMoves.sort(Comparator.naturalOrder());

        assertArrayEquals(expectedMoves.toArray(), actualMoves.toArray());
    }
    private void testCornerMovement() {
        requisiteBoard = new ChessBoard();
        King king = new King(requisiteBoard, ChessPiece.Color.WHITE);
        requisiteBoard.placePiece(king, "a1");

        ArrayList<String> expectedMoves = new ArrayList<String>(Arrays.asList(new String[]{"a2", "b2", "b1"}));
        ArrayList<String> actualMoves = king.legalMoves();
        expectedMoves.sort(Comparator.naturalOrder());
        actualMoves.sort(Comparator.naturalOrder());

        assertArrayEquals(expectedMoves.toArray(), actualMoves.toArray());
    }
    private void testSharedSpaceMovement() {
        requisiteBoard = new ChessBoard();
        King king = new King(requisiteBoard, ChessPiece.Color.WHITE);
        requisiteBoard.placePiece(king, "b2");

        Pawn allyPawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        requisiteBoard.placePiece(allyPawn, "b1");
        Pawn foePawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        requisiteBoard.placePiece(foePawn, "b3");

        ArrayList<String> expectedMoves = new ArrayList<String>(Arrays.asList(new String[]{"a1", "a2", "a3", "b3", "c1", "c2", "c3"}));
        ArrayList<String> actualMoves = king.legalMoves();
        expectedMoves.sort(Comparator.naturalOrder());
        actualMoves.sort(Comparator.naturalOrder());

        assertArrayEquals(expectedMoves.toArray(), actualMoves.toArray());
    }
}
