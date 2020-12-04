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



        return board.toString();
    }

    private static void trimProperly(ArrayList<String> board) {
        String replacement = board.get(0).substring(1, board.get(0).length() - 1);
        String[] temp = replacement.split(",");
        board.clear();
        Collections.addAll(board, temp);
    }


}
