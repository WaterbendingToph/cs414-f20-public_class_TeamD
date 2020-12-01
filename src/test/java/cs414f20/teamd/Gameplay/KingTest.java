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
}
