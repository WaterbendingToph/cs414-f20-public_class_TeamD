package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    ChessBoard requisiteBoard;

    @Test
    void testToString() {
        requisiteBoard = new ChessBoard();

        Pawn pawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2659", pawn.toString());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265F", pawn.toString());
    }

    @Test
    void legalMoves() {
        testFromStart();
        testFromNotStart();
    }
    private void testFromStart() {
        requisiteBoard = new ChessBoard();

        Pawn whitePawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        Pawn blackPawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);

        requisiteBoard.placePiece(whitePawn, "b1");
        requisiteBoard.placePiece(blackPawn, "b8");

        String[] whiteExpectedMoves = {"b2", "b3", "b4"};
        String[] blackExpectedMoves = {"b7", "b6", "b5"};

        TestHelper.assertExpectedMovesEqualLegalMoves(whiteExpectedMoves, whitePawn.legalMoves());
        TestHelper.assertExpectedMovesEqualLegalMoves(blackExpectedMoves, blackPawn.legalMoves());
    }
    private void testFromNotStart() {
        requisiteBoard = new ChessBoard();

        Pawn whitePawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        Pawn blackPawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        Queen obstacle = new Queen(requisiteBoard, ChessPiece.Color.BLACK);
        Queen target = new Queen(requisiteBoard, ChessPiece.Color.BLACK);

        requisiteBoard.placePiece(whitePawn, "b3");
        requisiteBoard.placePiece(blackPawn, "b6");
        requisiteBoard.placePiece(obstacle, "b4");
        requisiteBoard.placePiece(target, "c4");

        String[] whiteExpectedMoves = {"c4"};
        String[] blackExpectedMoves = {"b5"};

        TestHelper.assertExpectedMovesEqualLegalMoves(whiteExpectedMoves, whitePawn.legalMoves());
        TestHelper.assertExpectedMovesEqualLegalMoves(blackExpectedMoves, blackPawn.legalMoves());
    }
}
