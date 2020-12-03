package cs414f20.teamd.Gameplay;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class ChampionTest {

	@Test
	void testToString() {
		ChessBoard testBoard = new ChessBoard();
		Champion testWhiteChampion = new Champion(testBoard, ChessPiece.Color.WHITE);
		Champion testBlackChampion = new Champion(testBoard, ChessPiece.Color.BLACK);

		assertTrue(testWhiteChampion.toString() == "\u2616");
		assertTrue(testBlackChampion.toString() == "\u2617");
	}
	
	@Test
	void legalMoves() {
		ChessBoard testBoard = new ChessBoard();
		Champion champion = new Champion(testBoard, ChessPiece.Color.BLACK);
		legalMovesFromOpenCenter(testBoard, champion);

		testBoard = new ChessBoard();
		champion = new Champion(testBoard, ChessPiece.Color.BLACK);
		legalMovesDiagonalHopping(testBoard, champion);

		testBoard = new ChessBoard();
		champion = new Champion(testBoard, ChessPiece.Color.BLACK);
		legalMovesCardinalHopping(testBoard, champion);
	}
	private void legalMovesFromOpenCenter(ChessBoard testBoard, Champion champion) {
		testBoard.placePiece(champion, "c2");
		String[] expectedMovesArray = {"a2", "b2", "d2", "e2", "c0", "c1", "c3", "c4", "a0", "a4", "e0", "e4"};
		TestHelper.assertExpectedMovesEqualLegalMoves(expectedMovesArray, champion.legalMoves());
	}
	private void legalMovesDiagonalHopping(ChessBoard testBoard, Champion champion) {
		Pawn hoppableEnemy = new Pawn(testBoard, ChessPiece.Color.WHITE);
		Pawn targetEnemy = new Pawn(testBoard, ChessPiece.Color.WHITE);
		Pawn hoppableAlly = new Pawn(testBoard, ChessPiece.Color.BLACK);
		Pawn blockingAlly = new Pawn(testBoard, ChessPiece.Color.BLACK);

		testBoard.placePiece(targetEnemy, "w1");
		testBoard.placePiece(hoppableEnemy, "a0");
		testBoard.placePiece(champion, "b1");
		testBoard.placePiece(hoppableAlly, "c2");
		testBoard.placePiece(blockingAlly, "d3");

		String[] expectedMovesArray = {"w1", "b0", "b2", "b3", "a1", "c1", "d1"};
		TestHelper.assertExpectedMovesEqualLegalMoves(expectedMovesArray, champion.legalMoves());
	}
	private void legalMovesCardinalHopping(ChessBoard testBoard, Champion champion) {
		Pawn blockingEnemy = new Pawn(testBoard, ChessPiece.Color.WHITE);
		Pawn blockedEnemy1 = new Pawn(testBoard, ChessPiece.Color.WHITE);
		Pawn blockingAlly = new Pawn(testBoard, ChessPiece.Color.BLACK);
		Pawn blockedEnemy2 = new Pawn(testBoard, ChessPiece.Color.WHITE);
		Pawn targetEnemy = new Pawn(testBoard, ChessPiece.Color.WHITE);

		testBoard.placePiece(champion, "h2");
		testBoard.placePiece(blockingEnemy, "h3");
		testBoard.placePiece(blockedEnemy1, "h4");
		testBoard.placePiece(blockingAlly, "g2");
		testBoard.placePiece(blockedEnemy2, "f2");
		testBoard.placePiece(targetEnemy, "h0");

		String[] expectedMovesArray = {"h4", "h3", "h1", "h0", "i2", "j2", "j0", "j4", "f0", "f2", "f4"};
		TestHelper.assertExpectedMovesEqualLegalMoves(expectedMovesArray, champion.legalMoves());
	}
}
