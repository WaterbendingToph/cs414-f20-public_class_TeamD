package cs414f20.teamd.Registration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @GetMapping("/register")
    public Registration register(@RequestParam String userID, String password) {
        Registration currentRegistration = new Registration(userID, password);
        currentRegistration.registerUser();
        return currentRegistration;
    }
}