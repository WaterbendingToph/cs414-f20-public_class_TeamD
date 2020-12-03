package cs414f20.teamd.Gameplay;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {
    private ChessBoard chessBoard;

    @org.junit.jupiter.api.Test
    void initializeTest() {
        chessBoard = new ChessBoard();
        chessBoard.initialize();

        initializeTestPawns();
        initializeTestCastleFolk();
        initializeTestWizards();
    }
    private void initializeTestPawns() {
        ChessPiece testPiece = null;

        for (char letter = 'a'; letter <= 'j'; letter++){
            try {
                testPiece = chessBoard.getPiece("" + letter + '1');
            } catch (IllegalPositionException ipe) { fail(); }

            assertEquals(Pawn.class, testPiece.getClass());
            assertEquals(ChessPiece.Color.WHITE, testPiece.color);

            try {
                testPiece = chessBoard.getPiece("" + letter + '8');
            } catch (IllegalPositionException ipe) {fail(); }

            assertEquals(Pawn.class, testPiece.getClass());
            assertEquals(ChessPiece.Color.BLACK, testPiece.color);
        }
    }
    private void initializeTestCastleFolk() {
        ArrayList<Class> blackRow = new ArrayList<>();
        ArrayList<Class> whiteRow = new ArrayList<>();
        ArrayList<ArrayList<Class>> rows = new ArrayList<>();
        rows.add(blackRow);
        rows.add(whiteRow);

        for (ArrayList<Class> row : rows) {
            row.add(Champion.class);
            row.add(Rook.class);
            row.add(Knight.class);
            row.add(Bishop.class);
            row.add(Queen.class);
            row.add(King.class);
            row.add(Bishop.class);
            row.add(Knight.class);
            row.add(Rook.class);
            row.add(Champion.class);

            char number = '9';
            if (row == whiteRow)
                number = '0';

            ChessPiece testPiece = null;
            for (char letter = 'a'; letter <= 'j'; letter++){
                try {
                    testPiece = chessBoard.getPiece("" + letter + number);
                } catch (IllegalPositionException ipe) { fail(); }

                Class testClass = row.get((int) letter - 'a');
                assertEquals(testClass, testPiece.getClass());
            }
        }
    }
    private void initializeTestWizards() {
        try {
            ChessPiece testPiece = chessBoard.getPiece("w1");
            assertEquals(Wizard.class, testPiece.getClass());
            assertEquals(ChessPiece.Color.WHITE, testPiece.color);

            testPiece = chessBoard.getPiece("w2");
            assertEquals(Wizard.class, testPiece.getClass());
            assertEquals(ChessPiece.Color.WHITE, testPiece.color);

            testPiece = chessBoard.getPiece("w3");
            assertEquals(Wizard.class, testPiece.getClass());
            assertEquals(ChessPiece.Color.BLACK, testPiece.color);

            testPiece = chessBoard.getPiece("w4");
            assertEquals(Wizard.class, testPiece.getClass());
            assertEquals(ChessPiece.Color.BLACK, testPiece.color);
        } catch (IllegalPositionException e) { fail(); }
    }

    @org.junit.jupiter.api.Test
    void placePieceTest() {
        chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.BLACK);
        chessBoard.placePiece(bishop, "a3");

        ChessPiece testPiece1 = null;
        ChessPiece testPiece2 = null;
        try {
            testPiece1 = chessBoard.getPiece("a3");
            testPiece2 = chessBoard.getPiece("h3");
        } catch (IllegalPositionException ipe) { fail(); }

        assertEquals(bishop, testPiece1);
        assertNotEquals(bishop, testPiece2);

        King king = new King(chessBoard, ChessPiece.Color.WHITE);
        chessBoard.placePiece(king, "w1");

        try {
            testPiece1 = chessBoard.getPiece("w1");
            testPiece2 = chessBoard.getPiece("w3");
        } catch (IllegalPositionException ipe){ fail(); }

        assertEquals(king, testPiece1);
        assertNotEquals(king, testPiece2);
    }

    @org.junit.jupiter.api.Test
    void moveTest() {
        chessBoard = new ChessBoard();
        chessBoard.initialize();

        //Test a valid move
        try {
            try {
                assertEquals(Pawn.class, chessBoard.getPiece("e1").getClass());
                chessBoard.move("e1", "e2");
                assertTrue(Helper.positionIsEmpty(chessBoard, "e1"));
                assertEquals(Pawn.class, chessBoard.getPiece("e2").getClass());

                assertEquals(Pawn.class, chessBoard.getPiece("b1").getClass());
                chessBoard.move("b1", "b2");
                assertTrue(Helper.positionIsEmpty(chessBoard, "b1"));
                assertEquals(Pawn.class, chessBoard.getPiece("b2").getClass());

                assertEquals(Pawn.class, chessBoard.getPiece("f1").getClass());
                chessBoard.move("f1", "f2");
                assertTrue(Helper.positionIsEmpty(chessBoard, "f1"));
                assertEquals(Pawn.class, chessBoard.getPiece("f2").getClass());
            } catch (IllegalPositionException e) { fail(); }
        } catch (IllegalMoveException e) { fail(); }

        //Test an invalid move
        try {
            assertThrows(IllegalMoveException.class, () -> chessBoard.move("d1", "e2"));

            assertEquals(Pawn.class, chessBoard.getPiece("a1").getClass());
            assertThrows(IllegalMoveException.class, () -> chessBoard.move("a1", "e8"));

            assertEquals(Pawn.class, chessBoard.getPiece("g1").getClass());
            assertTrue(Helper.positionIsEmpty(chessBoard, "g3"));
            assertThrows(IllegalMoveException.class, () -> chessBoard.move("g1", "h3"));
            assertTrue(Helper.positionIsEmpty(chessBoard, "g3"));
        } catch (IllegalPositionException e) { fail(); }
    }
}
