package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

public class Util {

    private static ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(method = GET, value = "/login")
    public static Map jsonToMap(String pBody) {
        try {
            return  mapper.readValue(pBody, new TypeReference<Map<String,String>>(){});
        }
        catch (Exception e) {
            e.printStackTrace();
        }

       return null;
    }


}
