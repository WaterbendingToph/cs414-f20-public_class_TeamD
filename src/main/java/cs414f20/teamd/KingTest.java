package a2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import a2.ChessPiece.Color;

class KingTest {

	@Test
	void testDefaultKingColor() {
		ChessBoard testBoard = new ChessBoard();
		King testKing = new King();
		King testBlackKing = new King(testBoard, Color.BLACK);
		
		assertTrue(testKing.color == Color.WHITE);
		assertTrue(testBlackKing.color == Color.BLACK);
	}
	
	@Test
	void testDefaultKingString() {
		ChessBoard testBoard = new ChessBoard();
		King testKing = new King();
		King testBlackKing = new King(testBoard, Color.BLACK);
		String testWhiteKingString = testKing.toString();
		String testBlackKingString = testBlackKing.toString();
		
		assertTrue(testWhiteKingString == "\u2654");
		assertTrue(testBlackKingString == "\u265A");
	}

}
