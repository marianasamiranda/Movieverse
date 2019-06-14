package controller;

import log.LogMethod;
import business.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class MiscController {

    @Autowired
    private MiscService miscService;


    @LogMethod
    @RequestMapping(method = GET, value = "/frontpage")
    public ResponseEntity<Object> frontpage() {
        try {
            return Util.ok(miscService.frontPageInfo());
        }
        catch (Exception e) {
            e.printStackTrace();
            return Util.badRequest("");
        }
    }
}
