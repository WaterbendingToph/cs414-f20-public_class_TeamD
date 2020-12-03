package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        //testCastling();

    }

    private void testFreeMovement() {
        requisiteBoard = new ChessBoard();
        King king = new King(requisiteBoard, ChessPiece.Color.WHITE);
        requisiteBoard.placePiece(king, "b2");

        String[] expectedMoves = {"a1", "a2", "a3", "b1", "b3", "c1", "c2", "c3"};

        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, king.legalMoves());
    }
    private void testCornerMovement() {
        requisiteBoard = new ChessBoard();
        King king = new King(requisiteBoard, ChessPiece.Color.WHITE);
        requisiteBoard.placePiece(king, "a0");

        String[] expectedMoves = {"a1", "b0", "b1", "w1"};

        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, king.legalMoves());
    }
    private void testSharedSpaceMovement() {
        requisiteBoard = new ChessBoard();
        King king = new King(requisiteBoard, ChessPiece.Color.WHITE);
        requisiteBoard.placePiece(king, "b2");

        Pawn allyPawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        requisiteBoard.placePiece(allyPawn, "b1");
        Pawn foePawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        requisiteBoard.placePiece(foePawn, "b3");

        String[] expectedMoves = {"a1", "a2", "a3", "b3", "c1", "c2", "c3"};

        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, king.legalMoves());
    }

    private void testCastling() {
        testCastlingLeft();
        testCastlingRight();
    }
    private void testCastlingLeft() {
        requisiteBoard = new ChessBoard();

        King king = new King(requisiteBoard, ChessPiece.Color.WHITE);
        Rook rook = new Rook(requisiteBoard, ChessPiece.Color.WHITE);

        requisiteBoard.placePiece(king, "f0");
        requisiteBoard.placePiece(rook, "b0");

        String[] expectedMoves = {"d0", "e0", "e1", "f1", "g0", "g1"};

        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, king.legalMoves());


        try {
            requisiteBoard.move("f0", "d0");
        } catch (IllegalMoveException e) { fail(); }

        try {
            ChessPiece testPiece = requisiteBoard.getPiece("d0");
            assertEquals(king, testPiece);

            testPiece = requisiteBoard.getPiece("e0");
            assertEquals(rook, testPiece);
        } catch (IllegalPositionException e) { fail(); }
    }
    private void testCastlingRight() {
        requisiteBoard = new ChessBoard();

        King king = new King(requisiteBoard, ChessPiece.Color.BLACK);
        Rook rook = new Rook(requisiteBoard, ChessPiece.Color.BLACK);

        requisiteBoard.placePiece(king, "f9");
        requisiteBoard.placePiece(rook, "i9");

        String[] expectedMoves = {"e8", "e9", "f8", "g8", "g9", "h9"};

        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, king.legalMoves());


        try {
            requisiteBoard.move("f9", "h9");
        } catch (IllegalMoveException e) { fail(); }

        try {
            ChessPiece testPiece = requisiteBoard.getPiece("h9");
            assertEquals(king, testPiece);

            testPiece = requisiteBoard.getPiece("g9");
            assertEquals(rook, testPiece);
        } catch (IllegalPositionException e) { fail(); }
    }

    //@Test
    void inCheck() {
        requisiteBoard = new ChessBoard();

        King king = new King(requisiteBoard, ChessPiece.Color.WHITE);
        Pawn enemyPawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);

        requisiteBoard.placePiece(king, "a0");
        assertFalse(king.inCheck());

        requisiteBoard.placePiece(enemyPawn, "b1");
        assertTrue(king.inCheck());
    }
}
