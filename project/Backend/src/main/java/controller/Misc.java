package controller;

import business.MiscManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class Misc {

    @Autowired
    private MiscManager miscManager;


    @RequestMapping(method = GET, value = "/frontpage")
    public ResponseEntity<Object> frontpage() {
        try {
            return Util.ok(miscManager.frontPageInfo());
        }
        catch (Exception e) {
            e.printStackTrace();
            return Util.badRequest("");
        }
    }
    
}
