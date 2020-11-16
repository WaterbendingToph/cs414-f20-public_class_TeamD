package cs414f20.teamd.Lobby;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LobbyController {
    
    @GetMapping("/lobby")
    public Lobby lobby(@RequestParam String userID, String password) {
        Lobby currentLobby = new Lobby(userID, password);
        currentLobby.attemptLogin();
        System.out.println(currentLobby);
        return currentLobby;
    }
}
