package cs414f20.teamd.Gameplay;

import cs414f20.teamd.DatabaseConnection.Database;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.json.*;

import java.util.ArrayList;
import java.util.Collections;

@RestController
public class GameplayController {

    @GetMapping("/getPlayerColor")
    public String getPlayerColor(@RequestParam(value = "gameID")String gameID, @RequestParam(value = "current")String current){
        return Database.getUserColor(gameID, current);
    }

    @GetMapping("/move")
    public MovePiece move(@RequestParam(value = "gameID")String gameID, @RequestParam(value = "from")String from, @RequestParam(value = "to")String to) {
        return new MovePiece(gameID, from, to);
    }

    @GetMapping("/getWhoseTurn")
    public String getWhoseTurn(@RequestParam(value = "gameID")String gameID) {
        return Database.getWhoseTurn(gameID);
    }

    @GetMapping("/getBoardState")
    public String getBoardState(@RequestParam(value = "gameID")String gameID) {
        ArrayList<String> boardState = Database.getBoardState(gameID);
        trimProperly(boardState);
        JSONObject pieces = new JSONObject();
        for(String square : boardState){
            String[] pieceAndPosition = square.split("=");
            pieces.put(pieceAndPosition[0], pieceAndPosition[1]);
        }
        return pieces.toString();
    }

    private static void trimProperly(ArrayList<String> board) {
        String replacement = board.get(0).substring(1, board.get(0).length() - 1);
        String[] temp = replacement.split(",");
        for(int i = 0; i<temp.length; i++)
            temp[i] = temp[i].trim();
        board.clear();
        Collections.addAll(board, temp);
    }


}
