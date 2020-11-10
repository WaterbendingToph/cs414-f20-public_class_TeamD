package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChessPieceTest {
    ChessBoard requisiteBoard;

    @Test
    void getColor() {
        requisiteBoard = new ChessBoard();

        Pawn pawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        assertEquals(ChessPiece.Color.WHITE, pawn.getColor());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        assertEquals(ChessPiece.Color.BLACK, pawn.getColor());
    }

    @Test
    void getPosition() {
        requisiteBoard = new ChessBoard();

        Pawn pawn = new Pawn(requisiteBoard, ChessPiece.Color.BLACK);
        requisiteBoard.placePiece(pawn, "a3");
        assertEquals("a3", pawn.getPosition());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        requisiteBoard.placePiece(pawn, "c1");
        assertEquals("c1", pawn.getPosition());
    }

    @Test
    void setPosition() {
        requisiteBoard = new ChessBoard();

        Pawn pawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        try { pawn.setPosition("f4"); } catch (IllegalPositionException e) {}
        assertEquals("f4", pawn.getPosition());

        pawn = new Pawn(requisiteBoard, ChessPiece.Color.WHITE);
        try { pawn.setPosition("b7"); } catch (IllegalPositionException e) {}
        assertEquals("b7", pawn.getPosition());
    }

    @Test
    void legalMoves() {
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