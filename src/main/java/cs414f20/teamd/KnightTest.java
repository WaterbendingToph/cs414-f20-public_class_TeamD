package a2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import a2.ChessPiece.Color;

class KnightTest {

	@Test
	void testDefaultKnightColor() {
		ChessBoard testBoard = new ChessBoard();
		Knight testKnight = new Knight();
		Knight testBlackKnight = new Knight(testBoard, Color.BLACK);
		
		assertTrue(testKnight.color == Color.WHITE);
		assertTrue(testBlackKnight.color == Color.BLACK);
	}
	
	@Test
	void testDefaultKnightString() {
		ChessBoard testBoard = new ChessBoard();
		Knight testKnight = new Knight();
		Knight testBlackKnight = new Knight(testBoard, Color.BLACK);
		String testWhiteKnightString = testKnight.toString();
		String testBlackKnightString = testBlackKnight.toString();
		
		assertTrue(testWhiteKnightString == "\u2658");
		assertTrue(testBlackKnightString == "\u265E");
	}
	
	@Test
	void testValidMoves() {
		Knight testKnight = new Knight();
		ArrayList<String> validMoves = testKnight.legalMoves();
		
		assertTrue(validMoves.isEmpty());
	}

}
