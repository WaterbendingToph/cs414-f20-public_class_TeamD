package cs414f20.teamd.Login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    
    @GetMapping("/login")
    public Login login(@RequestParam String userID, String password) {
        return new Login(userID, password);
    }
}
