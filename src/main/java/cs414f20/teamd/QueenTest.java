package a2;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import a2.ChessPiece.Color;

class QueenTest {

	@Test
	void testDefaultQueenColor() {
		ChessBoard testBoard = new ChessBoard();
		Queen testQueen = new Queen();
		Queen testBlackQueen = new Queen(testBoard, Color.BLACK);
		
		assertTrue(testQueen.color == Color.WHITE);
		assertTrue(testBlackQueen.color == Color.BLACK);
	}
	
	@Test
	void testDefaultQueenString() {
		ChessBoard testBoard = new ChessBoard();
		Queen testQueen = new Queen();
		Queen testBlackQueen = new Queen(testBoard, Color.BLACK);
		String testWhiteQueenString = testQueen.toString();
		String testBlackQueenString = testBlackQueen.toString();
		
		assertTrue(testWhiteQueenString == "\u2655");
		assertTrue(testBlackQueenString == "\u265B");
	}
	
	@Test
	void testLegalMoves() {
		Queen testQueen = new Queen();
		ArrayList<String> validMoves = testQueen.legalMoves();
		
		assertTrue(validMoves.isEmpty());
	}

}
