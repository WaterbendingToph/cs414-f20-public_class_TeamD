package a2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import a2.ChessPiece.Color;

class BishopTest {

	@Test
	void testDefaultBishopColor() {
		ChessBoard testBoard = new ChessBoard();
		Bishop testBishop = new Bishop();
		Bishop testBlackBishop = new Bishop(testBoard, Color.BLACK);
		
		assertTrue(testBishop.color == Color.WHITE);
		assertTrue(testBlackBishop.color == Color.BLACK);
	}
	
	@Test
	void testDefaultBishopString() {
		ChessBoard testBoard = new ChessBoard();
		Bishop testBishop = new Bishop();
		Bishop testBlackBishop = new Bishop(testBoard, Color.BLACK);
		String testWhiteBishopString = testBishop.toString();
		String testBlackBishopString = testBlackBishop.toString();
		
		assertTrue(testWhiteBishopString == "\u2657");
		assertTrue(testBlackBishopString == "\u265D");
	}

}
