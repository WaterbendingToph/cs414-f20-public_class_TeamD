package cs414f20.teamd.Registration;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @GetMapping("/register")
    public Registration register(@RequestParam String userID, String password) {
        System.out.println("Current password before hash: " + password);
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("After hash: " + hash);
        Registration currentRegistration = new Registration(userID, hash);
        currentRegistration.registerUser();
        // System.out.println(currentRegistration);
        return currentRegistration;
    }
}