package cs414f20.teamd.Gameplay;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import cs414f20.teamd.Gameplay.ChessPiece.Color;

class KingTest {

	@Test
	void testDefaultKingColor() {
		ChessBoard testBoard = new ChessBoard();
		King testKing = new King();
		King testBlackKing = new King(testBoard, Color.BLACK);
		
		assertEquals(Color.WHITE, testKing.color);
		assertEquals(Color.BLACK, testBlackKing.color);
	}
	
	@Test
	void testDefaultKingString() {
		ChessBoard testBoard = new ChessBoard();
		King testKing = new King();
		King testBlackKing = new King(testBoard, Color.BLACK);
		String testWhiteKingString = testKing.toString();
		String testBlackKingString = testBlackKing.toString();
		
		assertEquals("\u2654", testWhiteKingString);
		assertEquals("\u265A", testBlackKingString);
	}

}
