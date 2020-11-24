package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChessPieceTest {
    ChessBoard requisiteBoard;

    @Test
    void getColorTest() {
        requisiteBoard = new ChessBoard();

        Pawn pawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        assertEquals(ChessPiece.Color.WHITE, pawn.getColor());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals(ChessPiece.Color.BLACK, pawn.getColor());
    }

    @Test
    void getPositionTest() {
        requisiteBoard = new ChessBoard();

        Pawn pawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        requisiteBoard.placePiece(pawn, "a3");
        assertEquals("a3", pawn.getPosition());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        requisiteBoard.placePiece(pawn, "c1");
        assertEquals("c1", pawn.getPosition());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        requisiteBoard.placePiece(pawn, "w1");
        assertEquals("w1", pawn.getPosition());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        requisiteBoard.placePiece(pawn, "d9");
        assertEquals("d9", pawn.getPosition());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        requisiteBoard.placePiece(pawn, "j0");
        assertEquals("j0", pawn.getPosition());
    }

    @Test
    void setPositionTest() {
        requisiteBoard = new ChessBoard();

        Pawn pawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        try { pawn.setPosition("f4"); } catch (IllegalPositionException e) { fail(); }
        assertEquals("f4", pawn.getPosition());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        try { pawn.setPosition("b7"); } catch (IllegalPositionException e) { fail(); }
        assertEquals("b7", pawn.getPosition());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        try { pawn.setPosition("c9"); } catch (IllegalPositionException e) { fail(); }
        assertEquals("c9", pawn.getPosition());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        try { pawn.setPosition("h0"); } catch (IllegalPositionException e) { fail(); }
        assertEquals("h0", pawn.getPosition());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        try { pawn.setPosition("w4"); } catch (IllegalPositionException e) { fail(); }
        assertEquals("w4", pawn.getPosition());
    }

    @Test
    void legalMovesTest() {     // TODO: DOESN'T THIS JUST TEST TO SEE IF THE LEGAL MOVES ARE ARRAYLISTS? CHECK WITH VICTOR ABOUT THAT LATER
        requisiteBoard = new ChessBoard();

        ChessPiece piece = new Bishop(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals(ArrayList.class, piece.legalMoves().getClass());

        piece = new King(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals(ArrayList.class, piece.legalMoves().getClass());

        piece = new Knight(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals(ArrayList.class, piece.legalMoves().getClass());

        piece = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals(ArrayList.class, piece.legalMoves().getClass());

        piece = new Queen(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals(ArrayList.class, piece.legalMoves().getClass());

        piece = new Rook(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals(ArrayList.class, piece.legalMoves().getClass());
    }
}