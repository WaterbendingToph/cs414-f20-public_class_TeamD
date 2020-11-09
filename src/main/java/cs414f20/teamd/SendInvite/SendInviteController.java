package cs414f20.teamd.SendInvite;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendInviteController {

    @GetMapping("/sendInvite")
    public SendInvite sendInvite(@RequestParam(value = "current") String current, @RequestParam(value = "player") String player) {
        return new SendInvite(current, player);
    }    
}
