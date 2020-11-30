package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;
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
        legalMovesOpenCenter(requisiteBoard, bishop);

        requisiteBoard = new ChessBoard();
        bishop = new Bishop(requisiteBoard, ChessPiece.Color.BLACK);
        legalMovesOpenCorner(requisiteBoard, bishop);

        requisiteBoard = new ChessBoard();
        bishop = new Bishop(requisiteBoard, ChessPiece.Color.BLACK);
        legalMovesCrowdedCenter(requisiteBoard, bishop);
    }

    void legalMovesOpenCenter(ChessBoard board, Bishop bishop) {
        board.placePiece(bishop, "f5");
        String[] tofu = {"w1", "a0", "b1", "c2", "d3", "e4", "g6", "h7", "i8", "j9", "w3", "b9", "c8", "d7", "e6", "g4", "h3", "i2", "j1"};
        TestHelper.assertExpectedMovesEqualLegalMoves(tofu, bishop.legalMoves());
    }
    void legalMovesOpenCorner(ChessBoard board, Bishop bishop) {
        board.placePiece(bishop, "w4");
        String[] expectedMoves = {"a9", "b8", "c7", "d6", "e5", "f4", "g3", "h2", "i1", "j0", "w2"};
        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, bishop.legalMoves());
    }
    void legalMovesCrowdedCenter(ChessBoard board, Bishop bishop) {
        Pawn targetEnemy = new Pawn(board, ChessPiece.Color.WHITE);
        Pawn blockingAlly = new Pawn(board, ChessPiece.Color.BLACK);
        Pawn blockingEnemy = new Pawn(board, ChessPiece.Color.WHITE);
        Pawn blockedEnemy = new Pawn(board, ChessPiece.Color.WHITE);
        Pawn targetEnemy2 = new Pawn(board, ChessPiece.Color.WHITE);
        Pawn blockedEnemy2 = new Pawn(board, ChessPiece.Color.WHITE);

        board.placePiece(bishop, "f5");
        board.placePiece(targetEnemy, "w1");
        board.placePiece(blockingAlly, "g4");
        board.placePiece(blockingEnemy, "e6");
        board.placePiece(blockedEnemy, "d7");
        board.placePiece(targetEnemy2, "i8");
        board.placePiece(blockedEnemy2, "w3");
        System.out.println(board.toString());

        String[] expectedMoves = {"w1", "a0", "b1", "c2", "d3", "e4", "g6", "h7", "i8", "e6"};

        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, bishop.legalMoves());
    }

}