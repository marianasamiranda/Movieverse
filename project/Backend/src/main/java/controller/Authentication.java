package controller;
import business.UsersManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class Authentication {

    @RequestMapping(method = POST, value = "/login")
    public ResponseEntity<Object> login(@RequestBody String body) {
        Map result = Util.jsonToMap(body);
        String username = ((String) result.get("username"));
        String password = (String) result.get("password");

        String r = UsersManager.login(username, password);

        return Util.response(r);
    }


    @RequestMapping(method = POST, value = "/register")
    public ResponseEntity<Object> register(@RequestBody String body) {
        Map result = Util.jsonToMap(body);

        String email = (String) result.get("email");
        String username = (String) result.get("username");
        String name = (String) result.get("name");
        String password = (String) result.get("password");
        String country = (String) result.get("country");
        String d = (String) result.get("birthdate");
        LocalDate birthdate = LocalDate.parse(d.substring(0, d.length()-2), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        char gender = ((String) result.get("gender")).charAt(0);

        String r = UsersManager.registerUser(email, username, name, password, country, birthdate, gender);

        return Util.response(r);
    }
}