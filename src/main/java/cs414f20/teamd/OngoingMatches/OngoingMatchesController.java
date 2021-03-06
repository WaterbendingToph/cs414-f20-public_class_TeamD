package cs414f20.teamd.OngoingMatches;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OngoingMatchesController {
    
    @GetMapping("/matches")
    public OngoingMatches matches(@RequestParam String userID, String password) {
        OngoingMatches currentMatches = new OngoingMatches(userID, password);
        currentMatches.queryDatabase();
        System.out.println(currentMatches);
        return currentMatches;
    }
}