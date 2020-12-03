package cs414f20.teamd.Gameplay;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


class WizardTest {

	@Test
	void testToString() {
		ChessBoard testBoard = new ChessBoard();
		Wizard testWhiteWizard = new Wizard(testBoard, ChessPiece.Color.WHITE);
		Wizard testBlackWizard = new Wizard(testBoard, ChessPiece.Color.BLACK);
		
		assertTrue(testWhiteWizard.toString() == "\u263D");
		assertTrue(testBlackWizard.toString() == "\u263C");
	}
	
	@Test
	void legalMoves() {
		ChessBoard testBoard = new ChessBoard();
		Wizard wizard = new Wizard(testBoard, ChessPiece.Color.WHITE);
		legalMovesFromOpenCorner(testBoard, wizard);

		testBoard = new ChessBoard();
		wizard = new Wizard(testBoard, ChessPiece.Color.WHITE);
		legalMovesFromOpenCenter(testBoard, wizard);

		testBoard = new ChessBoard();
		wizard = new Wizard(testBoard, ChessPiece.Color.WHITE);
		legalMovesFromCrowdedCorner(testBoard, wizard);
	}
	private void legalMovesFromOpenCorner(ChessBoard testBoard, Wizard wizard) {
		testBoard.placePiece(wizard, "w1");
		String[] expectedMovesArray = {"a2", "a0", "c0"};
		assertExpectedMovesEqualLegalMoves(expectedMovesArray, wizard.legalMoves());
	}
	private void legalMovesFromOpenCenter(ChessBoard testBoard, Wizard wizard) {
		testBoard.placePiece(wizard, "d3");
		String[] expectedMovesArray = {"a4", "a2", "c6", "c4", "c2", "c0", "e6", "e4", "e2", "e0", "g4", "g2"};
		assertExpectedMovesEqualLegalMoves(expectedMovesArray, wizard.legalMoves());
	}
	private void legalMovesFromCrowdedCorner(ChessBoard testBoard, Wizard wizard) {
		Pawn enemy = new Pawn(testBoard, ChessPiece.Color.BLACK);
		Pawn ally = new Pawn(testBoard, ChessPiece.Color.WHITE);

		testBoard.placePiece(wizard, "w2");
		testBoard.placePiece(enemy, "j2");
		testBoard.placePiece(ally, "h0");

		String[] expectedMovesArray = {"j0", "j2"};
		assertExpectedMovesEqualLegalMoves(expectedMovesArray, wizard.legalMoves());
	}

	private void assertExpectedMovesEqualLegalMoves(String[] expectedMovesArray, ArrayList<String> legalMoves) {
		ArrayList<String> expectedMoves = new ArrayList<String>(Arrays.asList(expectedMovesArray));
		ArrayList<String> actualMoves = legalMoves;
		expectedMoves.sort(Comparator.naturalOrder());
		actualMoves.sort(Comparator.naturalOrder());

		assertArrayEquals(expectedMoves.toArray(), actualMoves.toArray());
	}//NOTE: DUPLICATED WITH ChampionTest
}
