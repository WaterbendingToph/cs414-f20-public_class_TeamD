package cs414f20.teamd.Gameplay;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import cs414f20.teamd.Gameplay.ChessPiece.Color;

class RookTest {

	@Test
	void testDefaultRookColor() {
		ChessBoard testBoard = new ChessBoard();
		Rook testRook = new Rook();
		Rook testBlackRook = new Rook(testBoard, Color.BLACK);
		
		assertEquals(Color.WHITE, testRook.color);
		assertEquals(Color.BLACK, testBlackRook.color);
	}
	
	@Test
	void testDefaultRookString() {
		ChessBoard testBoard = new ChessBoard();
		Rook testRook = new Rook();
		Rook testBlackRook = new Rook(testBoard, Color.BLACK);
		String testWhiteRookString = testRook.toString();
		String testBlackRookString = testBlackRook.toString();
		
		assertEquals("\u2656", testWhiteRookString);
		assertEquals("\u265C", testBlackRookString);
	}

}
