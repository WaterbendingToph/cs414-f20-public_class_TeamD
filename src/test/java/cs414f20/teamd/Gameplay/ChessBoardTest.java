package cs414f20.teamd.Gameplay;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {
    private ChessBoard chessBoard;

    @org.junit.jupiter.api.Test
    void initializeTest() {
        chessBoard = new ChessBoard();
        chessBoard.initialize();
        ChessPiece testPiece = null;

        for (char letter = 'a'; letter <= 'i'; letter++){
            try{testPiece = chessBoard.getPiece("" + letter + '1');} catch (IllegalPositionException ipe){ fail(); }
            assertEquals(testPiece.getClass(), Pawn.class);
            assertEquals(testPiece.color, ChessPiece.Color.BLACK);

            try{testPiece = chessBoard.getPiece("" + letter + '8');} catch (IllegalPositionException ipe){fail(); }
            assertEquals(testPiece.getClass(), Pawn.class);
            assertEquals(testPiece.color, ChessPiece.Color.WHITE);
        }

        ArrayList<Class> blackRow = new ArrayList<>();
        ArrayList<Class> whiteRow = new ArrayList<>();
        ArrayList<ArrayList<Class>> rows = new ArrayList<>();
        rows.add(blackRow);
        rows.add(whiteRow);
        for (ArrayList<Class> row : rows){
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

            for (char letter = 'a'; letter <= 'i'; letter++){
                try{testPiece = chessBoard.getPiece("" + letter + number);} catch (IllegalPositionException ipe){}
                Class testClass = row.get((int)letter - 'a');
                assertEquals(testPiece.getClass(), testClass);
            }
        }
    }


    @org.junit.jupiter.api.Test
    void placePieceTest() {
        chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.BLACK);
        chessBoard.placePiece(bishop, "a3");

        ChessPiece testPiece1 = null;
        ChessPiece testPiece2 = null;
        try{testPiece1 = chessBoard.getPiece("a3");} catch (IllegalPositionException ipe){ fail(); }
        try{testPiece2 = chessBoard.getPiece("h3");} catch (IllegalPositionException ipe){ fail(); }

        assertEquals(bishop, testPiece1);
        assertNotEquals(bishop, testPiece2);

        King king = new King(chessBoard, ChessPiece.Color.WHITE);
        chessBoard.placePiece(king, "w1");

        testPiece1 = null;
        testPiece2 = null;

        try{testPiece1 = chessBoard.getPiece("w1");} catch (IllegalPositionException ipe){ fail(); }
        try{testPiece2 = chessBoard.getPiece("w3");} catch (IllegalPositionException ipe){ fail(); }

        assertEquals(king, testPiece1);
        assertNotEquals(king, testPiece2);
    }

    @org.junit.jupiter.api.Test
    void moveTest() {       // TODO: ADD IN CASES FOR PIECES IN HERE MOVED TO AND FROM THE NEW SPACES
        chessBoard = new ChessBoard();
        chessBoard.initialize();

        //Test a valid move
        try {
            try { assertEquals(Pawn.class, chessBoard.getPiece("e2").getClass()); } catch (IllegalPositionException e) { fail(); }
            chessBoard.move("e2", "e3");
            try { assertTrue(Helper.positionIsEmpty(chessBoard, "e2")); } catch (IllegalPositionException e) { fail(); }
            try { assertEquals(Pawn.class, chessBoard.getPiece("e3").getClass()); } catch (IllegalPositionException e) { fail(); }

            try { assertEquals(Pawn.class, chessBoard.getPiece("b2").getClass()); } catch (IllegalPositionException e) { fail(); }
            chessBoard.move("b2", "b4");
            try { assertTrue(Helper.positionIsEmpty(chessBoard, "b2")); } catch (IllegalPositionException e) { fail(); }
            try { assertEquals(Pawn.class, chessBoard.getPiece("b4").getClass()); } catch (IllegalPositionException e) { fail(); }

            try { assertEquals(Bishop.class, chessBoard.getPiece("f1").getClass()); } catch (IllegalPositionException e) { fail(); }
            chessBoard.move("f1", "a6");
            try { assertTrue(Helper.positionIsEmpty(chessBoard, "f1")); } catch (IllegalPositionException e) { fail(); }
            try { assertEquals(Bishop.class, chessBoard.getPiece("a6").getClass()); } catch (IllegalPositionException e) { fail(); }
        } catch (IllegalMoveException e) { fail(); }

        //Test an invalid move
        try {
            assertThrows(IllegalMoveException.class, () -> chessBoard.move("d1", "e2"));

            assertEquals(King.class, chessBoard.getPiece("e1").getClass());
            assertThrows(IllegalMoveException.class, () -> chessBoard.move("e1", "e8"));

            assertEquals(Knight.class, chessBoard.getPiece("g1").getClass());
            assertTrue(Helper.positionIsEmpty(chessBoard, "g3"));
            assertThrows(IllegalMoveException.class, () -> chessBoard.move("g1", "h3"));
            assertTrue(Helper.positionIsEmpty(chessBoard, "g3"));
        } catch (IllegalPositionException e) { fail(); }
    }
}