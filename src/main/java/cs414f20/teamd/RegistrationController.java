package cs414f20.teamd;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    private ArrayList<Player> registeredUsers;
    private String validIDRegex = "[a-z][A-Z][0-9]{1,16}";


    //Front-facing
    @GetMapping("/registration_request")
    public String UserIsRegistered(@RequestParam(value = "userID", defaultValue = "unknown") String requestedID){
        return "" + UserIsRegisteredInDatabase(requestedID);
    }
    public String RegisterUser(@RequestParam(value = "userID", defaultValue = "") String newID){
        if (!UserIDIsSanitary(newID))
            return "failure. Invalid ID submitted.";

        //TODO: connect with RegisterUserInDatabase() to actually register the usual.

        return "false";
    }


    //Back-facing
    private boolean UserIsRegisteredInDatabase(String userID){//UNFINISHED
        return true;
    }
    private void RegisterUserInDatabase(String id, String password){//UNFINISHED
        Player player = new Player(id, password);


    }


    //Utility
    private boolean UserIDIsSanitary(String potentialNewUserID){
        Pattern pattern = Pattern.compile(validIDRegex);
        Matcher matcher = pattern.matcher(potentialNewUserID);
        return matcher.matches();
    }
}
