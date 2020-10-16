package cs414f20.teamd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ChessPieceTest {

	@Test
	void testGetPosition() {
		Pawn testPiece = new Pawn();
		try {
			testPiece.setPosition("a1");
		} catch (IllegalPositionException e) {
		}
		
		String testPosition = testPiece.getPosition();
		
		assertTrue(testPosition == "a1");
	}
	
	@Test
	void testSetPosition() {
		Pawn testPiece = new Pawn();
		testPiece.row = 0;
		testPiece.column = 0;
		Boolean thrown = false;
		
		try {
			testPiece.setPosition("a2");
		} catch (IllegalPositionException e) {
			thrown = true;
		}
		String testPosition = testPiece.getPosition();
		
		assertTrue(testPosition == "a2");
		assertFalse(thrown);
	}
	
	@Test
	void testSetPositionException() {
		Pawn testPiece = new Pawn();
		testPiece.row = 0;
		testPiece.column = 0;
		Boolean thrown = false;
		
		try {
			testPiece.setPosition("z2");
		} catch (IllegalPositionException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}

}
