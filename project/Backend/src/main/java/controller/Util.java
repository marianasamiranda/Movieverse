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

    public static ResponseEntity<Object> ok(Object o) {
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    public static ResponseEntity<Object> badRequest(Object error) {
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
