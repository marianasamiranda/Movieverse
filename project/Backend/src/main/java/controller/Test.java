package controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class Test {

    private static final String template = "Hello, %s!";

    @RequestMapping(method=GET, value="/hello")
    public String hello (@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }

    @RequestMapping(method = GET, value = "/login2")
    public void loginSafe(@RequestBody String pBody) {

        Map result = Util.jsonToMap(pBody);

        System.out.println(result);
        //return String.format(template, name);
    }




}
