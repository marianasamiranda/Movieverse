package controller2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class Authentication {

    @RequestMapping(method = GET, value = "/open")
    public String open() {
        return "open";
    }

    @RequestMapping(method = GET , value = "/private")
    public String login() {
        return "private";
    }





}