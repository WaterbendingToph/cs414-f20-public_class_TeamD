package cs414f20.teamd.Gameplay;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import cs414f20.teamd.Gameplay.ChessPiece.Color;

class BishopTest {

	@Test
	void testDefaultBishopColor() {
		ChessBoard testBoard = new ChessBoard();
		Bishop testBishop = new Bishop();
		Bishop testBlackBishop = new Bishop(testBoard, Color.BLACK);
		
		assertEquals(Color.WHITE, testBishop.color);
		assertEquals(Color.BLACK, testBlackBishop.color);
	}
	
	@Test
	void testDefaultBishopString() {
		ChessBoard testBoard = new ChessBoard();
		Bishop testBishop = new Bishop();
		Bishop testBlackBishop = new Bishop(testBoard, Color.BLACK);
		String testWhiteBishopString = testBishop.toString();
		String testBlackBishopString = testBlackBishop.toString();
		
		assertEquals("\u2657", testWhiteBishopString);
		assertEquals("\u265D", testBlackBishopString);
	}

}
