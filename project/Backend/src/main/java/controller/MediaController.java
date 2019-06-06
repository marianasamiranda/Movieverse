package controller;

import business.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class MediaController {

    @Autowired
    MediaService mediaService;

    @RequestMapping(method = GET, value = "/media/{id}")
    public ResponseEntity<Object> getMedia(@PathVariable(value = "id", required = true) int id) {
        try {
            return Util.ok(mediaService.getMedia(id));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }

}
