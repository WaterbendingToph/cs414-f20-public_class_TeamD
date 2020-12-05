package cs414f20.teamd.Gameplay;

import cs414f20.teamd.DatabaseConnection.Database;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Collections;

@RestController
public class GameplayController {

    @GetMapping("/getWhoseTurn")
    public String getWhoseTurn(@RequestParam(value = "gameID")String gameID) {
        return Database.getWhoseTurn(gameID);
    }

    @GetMapping("/getBoardState")
    public String getBoardState(@RequestParam(value = "gameID")String gameID) {
        ArrayList<String> boardState = Database.getBoardState(gameID);
        trimProperly(boardState);
        ChessBoard board = new ChessBoard();
        board.populateBoard(boardState);

        String allSquareStates = new String();
        String toAdd = "empty,";
        String position;
        ChessPiece.Color white = ChessPiece.Color.WHITE;
        ChessPiece.Color black = ChessPiece.Color.BLACK;
        String letters = "abcedfghij";
        String numbers = "0123456789";
        try {
            for (int i = 9; i >= 0; i--) {
                for (int j = 9; j >= 0; j--) {
                    position = "";
                    position += letters.charAt(j);
                    position += numbers.charAt(i);
//                    System.err.println(position);

                    toAdd = "empty,";

                    if (Helper.positionIsEmpty(board, position)) {
                        // skip this else block
                    } else {
                        ChessPiece piece = board.getPiece(position);

                        if (piece instanceof Pawn && piece.color == white)
                            toAdd = "white pawn,";

                        else if (piece instanceof Pawn && piece.color == black)
                            toAdd = "black pawn,";

                        else if (piece instanceof Rook && piece.color == white)
                            toAdd = "white rook,";

                        else if (piece instanceof Rook && piece.color == black)
                            toAdd = "black rook,";

                        else if (piece instanceof Knight && piece.color == white)
                            toAdd = "white knight,";

                        else if (piece instanceof Knight && piece.color == black)
                            toAdd = "black knight,";

                        else if (piece instanceof Bishop && piece.color == white)
                            toAdd = "white bishop,";

                        else if (piece instanceof Bishop && piece.color == black)
                            toAdd = "black bishop,";

                        else if (piece instanceof Queen && piece.color == white)
                            toAdd = "white queen,";

                        else if (piece instanceof Queen && piece.color == black)
                            toAdd = "black queen,";

                        else if (piece instanceof King && piece.color == white)
                            toAdd = "white king,";

                        else if (piece instanceof King && piece.color == black)
                            toAdd = "black king,";

                        else if (piece instanceof Champion && piece.color == white)
                            toAdd = "white champion,";

                        else if (piece instanceof Champion && piece.color == black)
                            toAdd = "black champion,";

                        else if (piece instanceof Wizard && piece.color == white)
                            toAdd = "white wizard,";

                        else if (piece instanceof Wizard && piece.color == black)
                            toAdd = "black wizard,";
                    }
                    allSquareStates = allSquareStates.concat(toAdd);
//                    System.err.println("toAdd is \"" + toAdd + "\"");

                }
            }
        } catch (IllegalPositionException ipe) {
            System.err.println("Error while getting pieces in getBoardState of GameplayController" + toAdd);
        }

        return allSquareStates;
    }

    private static void trimProperly(ArrayList<String> board) {
        String replacement = board.get(0).substring(1, board.get(0).length() - 1);
        String[] temp = replacement.split(",");
        board.clear();
        Collections.addAll(board, temp);
    }


}
