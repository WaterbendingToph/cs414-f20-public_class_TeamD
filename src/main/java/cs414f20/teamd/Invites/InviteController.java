package cs414f20.teamd.Invites;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cs414f20.teamd.DatabaseConnection.Database;

@RestController
public class InviteController {

    @GetMapping("/sendInvite")
    public SendInvite sendInvite(@RequestParam(value = "current") String current, @RequestParam(value = "player") String player) {
        return new SendInvite(current, player);
    }   
    
    @GetMapping("/getInvites")
    public GetInvites getInvites(@RequestParam(value = "current") String current) {
        return new GetInvites(current);
    }

    @GetMapping("/deleteInvite")
    public boolean deleteInvite(@RequestParam(value = "current") String current, @RequestParam(value = "player") String player) {
        return Database.deleteInvite(current, player);
    }
}
