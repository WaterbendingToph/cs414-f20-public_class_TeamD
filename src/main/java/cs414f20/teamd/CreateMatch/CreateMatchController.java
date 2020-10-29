package cs414f20.teamd.CreateMatch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class CreateMatchController {

    @GetMapping("/createMatch")
    public CreateMatch createMatch(@RequestParam(value = "playerID") String[] playerIDList) {
        Random r = new Random();
        long id = r.nextLong();
        System.out.println("List of players are: ");
        for(String name : playerIDList){
            System.out.println("\t-"+name);
        }
        return new CreateMatch(Math.abs(id), playerIDList[0]);
    }
}
