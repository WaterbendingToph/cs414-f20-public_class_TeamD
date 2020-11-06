package cs414f20.teamd.Gameplay;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import cs414f20.teamd.Gameplay.ChessPiece.Color;

class KnightTest {

	@Test
	void testDefaultKnightColor() {
		ChessBoard testBoard = new ChessBoard();
		Knight testKnight = new Knight();
		Knight testBlackKnight = new Knight(testBoard, Color.BLACK);
		
		assertEquals(Color.WHITE, testKnight.color);
		assertEquals(Color.BLACK, testBlackKnight.color);
	}
	
	@Test
	void testDefaultKnightString() {
		ChessBoard testBoard = new ChessBoard();
		Knight testKnight = new Knight();
		Knight testBlackKnight = new Knight(testBoard, Color.BLACK);
		String testWhiteKnightString = testKnight.toString();
		String testBlackKnightString = testBlackKnight.toString();
		
		assertEquals("\u2658", testWhiteKnightString);
		assertEquals("\u265E", testBlackKnightString);
	}
	
	@Test
	void testValidMoves() {
		Knight testKnight = new Knight();
		ArrayList<String> validMoves = testKnight.legalMoves();
		
		assertTrue(validMoves.isEmpty());
	}

}
