package cs414f20.teamd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

	@Test
	void testConstructChessBoard() {
		ChessBoard testBoard = new ChessBoard();
		testBoard.initialize();

		assertNotNull(testBoard);
	}
	
	@Test
	void testGetPiece() {
		ChessBoard testBoard = new ChessBoard();
		testBoard.initialize();
		Boolean thrown = false;
		
		try {
			ChessPiece testPiece = testBoard.getPiece("a1");
			assertNotNull(testPiece);
		} catch (IllegalPositionException e) {
			thrown = true;
		}
		
		assertFalse(thrown);
	}
	
	@Test
	void testGetPieceException() {
		ChessBoard testBoard = new ChessBoard();
		testBoard.initialize();
		Boolean thrown = false;
		
		try {
			ChessPiece testPiece = testBoard.getPiece("z1");
			assertNotNull(testPiece);
		} catch (IllegalPositionException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}

}
