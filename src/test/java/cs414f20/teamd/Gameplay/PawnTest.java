package cs414f20.teamd.Gameplay;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import cs414f20.teamd.Gameplay.ChessPiece.Color;

class PawnTest {

	@Test
	void testDefaultPawnColor() {
		ChessBoard testBoard = new ChessBoard();
		Pawn testPawn = new Pawn();
		Pawn testBlackPawn = new Pawn(testBoard, Color.BLACK);
		
		assertEquals(Color.WHITE, testPawn.color);
		assertEquals(Color.BLACK, testBlackPawn.color);
	}
	
	@Test
	void testDefaultPawnString() {
		ChessBoard testBoard = new ChessBoard();
		Pawn testPawn = new Pawn();
		Pawn testBlackPawn = new Pawn(testBoard, Color.BLACK);
		String testWhitePawnString = testPawn.toString();
		String testBlackPawnString = testBlackPawn.toString();
		
		assertEquals("\u2659", testWhitePawnString);
		assertEquals("\u265F", testBlackPawnString);
	}
	
}
