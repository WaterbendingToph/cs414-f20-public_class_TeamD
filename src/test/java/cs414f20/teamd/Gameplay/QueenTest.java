package cs414f20.teamd.Gameplay;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import cs414f20.teamd.Gameplay.ChessPiece.Color;

class QueenTest {

	@Test
	void testDefaultQueenColor() {
		ChessBoard testBoard = new ChessBoard();
		Queen testQueen = new Queen();
		Queen testBlackQueen = new Queen(testBoard, Color.BLACK);
		
		assertEquals(Color.WHITE, testQueen.color);
		assertEquals(Color.BLACK, testBlackQueen.color);
	}
	
	@Test
	void testDefaultQueenString() {
		ChessBoard testBoard = new ChessBoard();
		Queen testQueen = new Queen();
		Queen testBlackQueen = new Queen(testBoard, Color.BLACK);
		String testWhiteQueenString = testQueen.toString();
		String testBlackQueenString = testBlackQueen.toString();
		
		assertEquals("\u2655", testWhiteQueenString);
		assertEquals("\u265B", testBlackQueenString);
	}
	
	@Test
	void testLegalMoves() {
		Queen testQueen = new Queen();
		ArrayList<String> validMoves = testQueen.legalMoves();
		
		assertTrue(validMoves.isEmpty());
	}

}
