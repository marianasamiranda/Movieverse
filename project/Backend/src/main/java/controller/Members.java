package controller;

import business.MemberManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class Members {

    @Autowired
    MemberManager memberManager;

    @RequestMapping(method = GET, value = "/people-search")
    public ResponseEntity<Object> search(@RequestParam(value = "name") String name) {
        try {
            return Util.ok(memberManager.search(name));
        }
        catch (Exception e) {
            return Util.badRequest("");
        }
    }
}
