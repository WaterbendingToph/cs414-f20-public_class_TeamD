package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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

        requisiteBoard.placePiece(whitePawn, "b2");
        requisiteBoard.placePiece(blackPawn, "b7");

        ArrayList<String> whiteExpectedMoves = new ArrayList<String>(Arrays.asList(new String[]{"b3", "b4"}));
        ArrayList<String> blackExpectedMoves = new ArrayList<String>(Arrays.asList(new String[]{"b6", "b5"}));
        ArrayList<String> whiteActualMoves = whitePawn.legalMoves();
        ArrayList<String> blackActualMoves = blackPawn.legalMoves();

        whiteExpectedMoves.sort(Comparator.naturalOrder());
        blackExpectedMoves.sort(Comparator.naturalOrder());
        whiteActualMoves.sort(Comparator.naturalOrder());
        blackActualMoves.sort(Comparator.naturalOrder());

        assertArrayEquals(whiteExpectedMoves.toArray(), whiteActualMoves.toArray());
        assertArrayEquals(blackExpectedMoves.toArray(), blackActualMoves.toArray());
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

        ArrayList<String> whiteExpectedMoves = new ArrayList<String>(Arrays.asList(new String[]{"c4"}));
        ArrayList<String> blackExpectedMoves = new ArrayList<String>(Arrays.asList(new String[]{"b5"}));

        assertArrayEquals(whiteExpectedMoves.toArray(), whitePawn.legalMoves().toArray());
        assertArrayEquals(blackExpectedMoves.toArray(), blackPawn.legalMoves().toArray());
    }
}