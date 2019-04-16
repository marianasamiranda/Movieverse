package controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class Controller {

    @RequestMapping(method = GET, value = "/private")
    public String p() {

        return "private";
    }

    @RequestMapping(method = GET, value = "/open")
    public String o() {

        return "open";
    }
}
