package cs414f20.teamd.Gameplay;

import cs414f20.teamd.DatabaseConnection.Database;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameplayController {

    @GetMapping("/getWhoseTurn")
    public String getWhoseTurn(@RequestParam(value = "gameID")String gameID) {
        return Database.getWhoseTurn(gameID);
    }

}
