package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    ChessBoard requisiteBoard;

    @Test
    void testToString() {
        requisiteBoard = new ChessBoard();

        Knight knight = new Knight(requisiteBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2658", knight.toString());

        knight = new Knight(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265E", knight.toString());
    }

    //@Test
    void legalMoves() {
        requisiteBoard = new ChessBoard();
        Knight knight = new Knight(requisiteBoard, ChessPiece.Color.WHITE);
        legalMovesOpenCenter(requisiteBoard, knight);

        requisiteBoard = new ChessBoard();
        knight = new Knight(requisiteBoard, ChessPiece.Color.WHITE);
        legalMovesCrowdedCenter(requisiteBoard, knight);

        requisiteBoard = new ChessBoard();
        knight = new Knight(requisiteBoard, ChessPiece.Color.WHITE);
        legalMovesCrowdedCorner(requisiteBoard, knight);
    }

    private void legalMovesOpenCenter(ChessBoard board, Knight knight) {
        board.placePiece(knight, "e4");
        String[] expectedMoves = {"c3", "c5", "d2", "d6", "f2", "f6", "g3", "g5"};
        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, knight.legalMoves());
    }
    private void legalMovesCrowdedCenter(ChessBoard board, Knight knight) {
        board.placePiece(knight, "e4");

        Pawn targetEnemy1 = new Pawn(board, ChessPiece.Color.BLACK);
        Pawn targetEnemy2 = new Pawn(board, ChessPiece.Color.BLACK);
        Pawn blockingAlly1 = new Pawn(board, ChessPiece.Color.WHITE);
        Pawn blockingAlly2 = new Pawn(board, ChessPiece.Color.WHITE);

        board.placePiece(targetEnemy1, "c3");
        board.placePiece(targetEnemy2, "c5");
        board.placePiece(blockingAlly1, "d2");
        board.placePiece(blockingAlly2, "d6");

        String[] expectedMoves = {"c3", "c5", "f2", "f6", "g3", "g5"};

        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, knight.legalMoves());
    }
    private void legalMovesCrowdedCorner(ChessBoard board, Knight knight) {
        Knight enemyKnight = new Knight(board, ChessPiece.Color.BLACK);

        board.placePiece(knight, "w2");
        board.placePiece(enemyKnight, "i0");

        String[] expectedMoves = {"i0", "j1"};
        String[] enemyExpectedMoves = {"g1", "h2", "j2", "w2"};

        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, knight.legalMoves());
        TestHelper.assertExpectedMovesEqualLegalMoves(enemyExpectedMoves, enemyKnight.legalMoves());
    }
}
