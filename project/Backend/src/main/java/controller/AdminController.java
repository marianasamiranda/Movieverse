package controller;

import business.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;


    //all stats
    @RequestMapping(method = GET, value = "/admin/stats")
    public ResponseEntity<Object> stats(@RequestHeader(value = "Authorization") String t,
                                        @RequestParam(value = "time", required = false) Integer time) {
        try {
            String token = t.split(" ")[1];
            return Util.ok(adminService.stats(token, time));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }


    //plots only
    @RequestMapping(method = GET, value = "/admin/stats/plots")
    public ResponseEntity<Object> plots(@RequestHeader(value = "Authorization") String t,
                                        @RequestParam(value = "time") Integer time) {
        try {
            String token = t.split(" ")[1];
            return Util.ok(adminService.plots(token, time));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }
}
