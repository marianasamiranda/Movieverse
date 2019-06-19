package controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Callable;

public class Util {

    private static ObjectMapper mapper = new ObjectMapper();

    public static ResponseEntity<Object> ok(Object o) {
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    public static ResponseEntity<Object> badRequest(Object error) {
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    public static ResponseEntity<Object> callServiceAndReturn(Callable<Object> callToService) {
        try {
            Object o = callToService.call();
            return ok(o);
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest("");
        }
    }

    public static ResponseEntity<Object> callServiceAndReturnError(Callable<Object> callToService) {
        try {
            Object o = callToService.call();
            return ok(o);
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest(e.getMessage());
        }
    }



}
