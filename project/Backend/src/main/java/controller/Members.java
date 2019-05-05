package controller;

import business.MemberManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = GET, value = "/member/{id}")
    public ResponseEntity<Object> profile(@PathVariable(value = "id", required = true) int id) {
        try {
            return Util.ok(memberManager.memberInfo(id));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }
}
