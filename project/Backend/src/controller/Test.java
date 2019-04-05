package controller;

import data.movieverse.MUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class Test {

    private static final String template = "Hello, %s!";

    @RequestMapping(method=GET, value="/hello")
    public String hello (@RequestParam(value="name", defaultValue="World") String name) {
        System.out.println("HELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLO");
        return String.format(template, name);
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = GET, value = "/login2")
    public void loginSafe(@RequestBody String pBody) {

        Map result = Util.jsonToMap(pBody);

        System.out.println(result);
        //return String.format(template, name);
    }




}
