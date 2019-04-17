package controller;

import business.UsersManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class Users {

    @RequestMapping(method = GET, value = "/profile")
    public ResponseEntity<Object> profile(@RequestHeader(value = "Authorization") String t) {
        String token = t.split(" ")[1];

        try {
            return Util.ok(UsersManager.profileInfo(token));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }

    @RequestMapping(method = PUT, value = "/avatar")
    public ResponseEntity<Object> avatar(@RequestHeader(value = "Authorization") String t,
                                         @RequestParam(value = "image") MultipartFile file) {
        String token = t.split(" ")[1];
        try {
            return Util.ok(UsersManager.newAvatar(token, file));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }
}
