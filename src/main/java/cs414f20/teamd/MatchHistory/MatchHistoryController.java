package cs414f20.teamd.MatchHistory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchHistoryController {
    @GetMapping("/history")
    public MatchHistory history(@RequestParam String userID, String password) {
        MatchHistory matchHistory = new MatchHistory(userID, password);
        matchHistory.queryDatabase();
        System.out.println(matchHistory);
        return matchHistory;
    }
}
