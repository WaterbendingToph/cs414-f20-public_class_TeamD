package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class RookTest {
    @Test
    void testToString() {
        ChessBoard requisiteBoard = new ChessBoard();

        Rook rook = new Rook(requisiteBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2656", rook.toString());

        rook = new Rook(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265C", rook.toString());
    }

    @Test
    void legalMoves() {
        ChessBoard board = new ChessBoard();
        Rook rook = new Rook(board, ChessPiece.Color.BLACK);
        legalMovesOpenCenter(board, rook);

        board = new ChessBoard();
        rook = new Rook(board, ChessPiece.Color.BLACK);
        legalMovesCrowdedCenter(board, rook);

        board = new ChessBoard();
        rook = new Rook(board, ChessPiece.Color.BLACK);
        legalMovesOpenCorner(board, rook);
    }

    void legalMovesOpenCenter(ChessBoard board, Rook rook) {
        board.placePiece(rook, "e4");
        String[] expectedMoves = {"e0", "e1", "e2", "e3", "e5", "e6", "e7", "e8", "e9",
                                  "a4", "b4", "c4", "d4", "f4", "g4", "h4", "i4", "j4"};
        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, rook.legalMoves());
    }
    void legalMovesCrowdedCenter(ChessBoard board, Rook rook) {
        Queen obstacle = new Queen(board, ChessPiece.Color.BLACK);
        Queen target = new Queen(board, ChessPiece.Color.WHITE);

        board.placePiece(obstacle, "a3");
        board.placePiece(rook, "c3");
        board.placePiece(target, "g3");

        String[] expectedMoves = {"b3", "c0", "c1", "c2", "c4", "c5", "c6", "c7", "c8", "c9", "d3", "e3", "f3", "g3"};

        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, rook.legalMoves());
    }
    void legalMovesOpenCorner(ChessBoard board, Rook rook) {
        board.placePiece(rook, "a0");
        String[] expectedMoves = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9",
                                  "b0", "c0", "d0", "e0", "f0", "g0", "h0", "i0", "j0"};
        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, rook.legalMoves());
    }
}
