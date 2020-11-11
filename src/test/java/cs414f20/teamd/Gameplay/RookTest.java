package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {
    ChessBoard requisiteBoard;

    @Test
    void testToString() {
        requisiteBoard = new ChessBoard();

        Rook rook = new Rook(requisiteBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2656", rook.toString());

        rook = new Rook(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265C", rook.toString());
    }

    @Test
    void legalMoves() {
        requisiteBoard = new ChessBoard();

        Rook rook = new Rook(requisiteBoard, ChessPiece.Color.BLACK);
        Queen obstacle = new Queen(requisiteBoard, ChessPiece.Color.BLACK);
        Queen target = new Queen(requisiteBoard, ChessPiece.Color.WHITE);

        requisiteBoard.placePiece(rook, "c3");
        requisiteBoard.placePiece(obstacle, "a3");
        requisiteBoard.placePiece(target, "g3");

        ArrayList<String> expectedMoves = new ArrayList<String>(Arrays.asList(new String[]{"b3", "c1", "c2", "c4", "c5", "c6", "c7", "c8", "d3", "e3", "f3", "g3"}));
        ArrayList<String> actualMoves = rook.legalMoves();

        expectedMoves.sort(Comparator.naturalOrder());
        actualMoves.sort(Comparator.naturalOrder());

        assertArrayEquals(expectedMoves.toArray(), actualMoves.toArray());
    }
}