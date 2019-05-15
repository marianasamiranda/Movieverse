package controller;

import business.MemberManager;
import business.MovieManager;
import business.UsersManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class Misc {

    @Autowired
    MemberManager memberManager;

    @Autowired
    MovieManager movieManager;

    @Autowired
    UsersManager usersManager;


    @RequestMapping(method = GET, value = "/frontpage")
    public ResponseEntity<Object> frontpage() {
        try {
            Map m = new HashMap<>();
            m.put("users", usersManager.estimatedCount());
            m.put("movies", movieManager.estimatedCount());
            m.put("members", memberManager.estimatedCount());
            m.put("comments", movieManager.estimatedNumberOfComments());
            m.put("releases", movieManager.latestMovies(0, 4));
            return Util.ok(m);
        }
        catch (Exception e) {
            e.printStackTrace();
            return Util.badRequest("");
        }
    }
    
}
