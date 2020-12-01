package cs414f20.teamd.Gameplay;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class QueenTest {
    @Test
    void testToString() {
        ChessBoard testBoard = new ChessBoard();

        Queen queen = new Queen(testBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2655", queen.toString());

        queen = new Queen(testBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265B", queen.toString());
    }

    @Test
    void legalMoves() {
        ChessBoard testBoard = new ChessBoard();
        Queen queen = new Queen(testBoard, ChessPiece.Color.WHITE);
        legalMovesOpenCenter(testBoard, queen);

        testBoard = new ChessBoard();
        queen = new Queen(testBoard, ChessPiece.Color.WHITE);
        legalMovesCrowdedCenter(testBoard, queen);

        testBoard = new ChessBoard();
        queen = new Queen(testBoard, ChessPiece.Color.WHITE);
        legalMovesOpenCorner(testBoard, queen);
    }

    private void legalMovesOpenCenter(ChessBoard board, Queen queen) {
        board.placePiece(queen, "f4");

        /*ArrayList<String> expectedMovesArrayList = new ArrayList<String>();
        for (char col = 'a'; col <= 'j'; col++)
            expectedMovesArrayList.add(col + "" + 4);
        for (int i = 0; i <= 9; i++)
            expectedMovesArrayList.add("f" + i);
        for (int i = -4; i <= 4; i++)
            expectedMovesArrayList.add((char)('f' + i) + "" + (4 + i));
        for (int i = -5; i <= 4; i++)
            expectedMovesArrayList.add((char)('f' + i) + "" + (4 - i));
        expectedMovesArrayList.add("w4");
        expectedMovesArrayList.add("w2");

        expectedMovesArrayList.sort(Comparator.naturalOrder());
        expectedMovesArrayList.remove("f4");
        expectedMovesArrayList.remove("f4");
        expectedMovesArrayList.remove("f4");
        expectedMovesArrayList.remove("f4");
        System.out.println(expectedMovesArrayList);*/

        String[] expectedMoves = {"a4", "a9", "b0", "b4", "b8", "c1", "c4", "c7", "d2", "d4", "d6", "e3", "e4", "e5",
                "f0", "f1", "f2", "f3", "f5", "f6", "f7", "f8", "f9", "g3", "g4", "g5", "h2", "h4", "h6", "i1", "i4",
                "i7", "j0", "j4", "j8", "w2", "w4"};

        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, queen.legalMoves());
    }
    private void legalMovesCrowdedCenter(ChessBoard board, Queen queen) {
        board.placePiece(queen, "f4");

        Pawn enemy1 = new Pawn(board, ChessPiece.Color.BLACK);
        Pawn enemy2 = new Pawn(board, ChessPiece.Color.BLACK);
        Pawn enemy3 = new Pawn(board, ChessPiece.Color.BLACK);
        Pawn ally1 = new Pawn(board, ChessPiece.Color.WHITE);
        Pawn ally2 = new Pawn(board, ChessPiece.Color.WHITE);
        Pawn ally3 = new Pawn(board, ChessPiece.Color.WHITE);

        board.placePiece(enemy1, "e3");
        board.placePiece(enemy2, "e4");
        board.placePiece(enemy3, "e5");
        board.placePiece(ally1, "g3");
        board.placePiece(ally2, "g4");
        board.placePiece(ally3, "g5");

        String[] expectedMoves = {"e3", "e4", "e5", "f0", "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9"};

        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, queen.legalMoves());
    }
    private void legalMovesOpenCorner(ChessBoard board, Queen queen) {
        board.placePiece(queen, "w3");
        String[] expectedMoves = {"w1", "a0", "b1", "c2", "d3", "e4", "f5", "g6", "h7", "i8", "j9"};
        TestHelper.assertExpectedMovesEqualLegalMoves(expectedMoves, queen.legalMoves());
    }
}
