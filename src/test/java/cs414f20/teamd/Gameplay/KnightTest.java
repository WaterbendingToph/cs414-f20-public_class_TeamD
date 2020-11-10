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

    @Test
    void legalMoves() {//TODO: Was not implemented for A2. So... implement.
        requisiteBoard = new ChessBoard();

        Knight knight = new Knight(requisiteBoard, ChessPiece.Color.WHITE);

        assertArrayEquals((new ArrayList<String>()).toArray(), knight.legalMoves().toArray());
    }
}