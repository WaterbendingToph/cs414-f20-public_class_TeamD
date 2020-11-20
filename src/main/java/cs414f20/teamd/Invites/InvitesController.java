package cs414f20.teamd.Invites;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cs414f20.teamd.RetrievePlayers;
import cs414f20.teamd.DatabaseConnection.Database;

@RestController
public class InvitesController {

    @GetMapping("/acceptInvite")
    public AcceptInvite acceptInvite(@RequestParam(value = "current") String current, @RequestParam(value = "player") String player) {
        return new AcceptInvite(current, player);
    }   

    @GetMapping("/getUser")
    public RetrievePlayers getUser(@RequestParam(value = "player") String player) {
        return new RetrievePlayers(player);
    }   

    @GetMapping("/sendInvite")
    public SendInvite sendInvite(@RequestParam(value = "current") String current, @RequestParam(value = "player") String[] players) {
        return new SendInvite(current, players);
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
