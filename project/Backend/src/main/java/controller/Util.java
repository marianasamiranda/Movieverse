package controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {

    private static ObjectMapper mapper = new ObjectMapper();

    public static ResponseEntity<Object> ok(Object o) {
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    public static ResponseEntity<Object> badRequest(Object error) {
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
