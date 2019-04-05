package controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import data.movieverse.MUser;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class Authentication {



    @RequestMapping(method = GET, value = "/login")
    public void login(@RequestBody String pBody) {

        Map result = Util.jsonToMap(pBody);

        System.out.println(result);
        //return String.format(template, name);
    }

    @RequestMapping(method = GET, value = "/register")
    public void register(@RequestBody String pBody) {

        Map result = Util.jsonToMap(pBody);

        System.out.println(result);
        //return String.format(template, name);
    }


}