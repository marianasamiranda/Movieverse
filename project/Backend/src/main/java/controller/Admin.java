package controller;

import business.AdminManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class Admin {

    @Autowired
    private AdminManager adminManager;


    @RequestMapping(method = GET, value = "/admin/stats")
    public ResponseEntity<Object> stats() {
        return Util.ok(adminManager.stats());
    }


    @RequestMapping(method = GET, value = "/admin/plots")
    public ResponseEntity<Object> plots(@RequestParam(value = "time", required = false) Integer time) {
        return Util.ok(adminManager.plotsData(time));
    }
}
