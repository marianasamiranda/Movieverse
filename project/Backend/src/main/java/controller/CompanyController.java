package controller;

import business.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(method = GET, value = "/company/{id}")
    public ResponseEntity<Object> companyProfile(@PathVariable(value = "id") int id) {
        return Util.callServiceAndReturn(() -> companyService.companyInfo(id));
    }

    @RequestMapping(method = GET, value = "/company/{id}/movies")
    public ResponseEntity<Object> companyMovies(@PathVariable(value = "id") int id,
                                                @RequestParam(value = "page") int page) {
        return Util.callServiceAndReturn(() -> companyService.companyMovies(id,page));
    }

}
