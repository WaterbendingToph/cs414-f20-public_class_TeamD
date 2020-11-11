package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    ChessBoard requisiteBoard;

    @Test
    void testToString() {
        requisiteBoard = new ChessBoard();

        Queen queen = new Queen(requisiteBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2655", queen.toString());

        queen = new Queen(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265B", queen.toString());
    }

    @Test
    void legalMoves() {//TODO: Was not implemented for A2. So... implement.
        requisiteBoard = new ChessBoard();

        Queen queen = new Queen(requisiteBoard, ChessPiece.Color.WHITE);

        assertArrayEquals((new ArrayList<String>()).toArray(), queen.legalMoves().toArray());
    }
}