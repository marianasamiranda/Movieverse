package controller;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class Util {

    private static ObjectMapper mapper = new ObjectMapper();

    public static Map jsonToMap(String pBody) {
        try {
            return mapper.readValue(pBody, new TypeReference<Map<String,String>>(){});
        }
        catch (Exception e) {
            e.printStackTrace();
        }

       return null;
    }

    public static ResponseEntity<Object> response(String r) {
        if (r == null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
    }
}
