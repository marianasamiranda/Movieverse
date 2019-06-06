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
    public ResponseEntity<Object> companyProfile(@PathVariable(value = "id", required = true) int id) {
        try {
            return Util.ok(companyService.companyInfo(id));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }

    @RequestMapping(method = GET, value = "/company-movies/{id}/{page}")
    public ResponseEntity<Object> companyMovies(@PathVariable(value = "id", required = true) int id, @PathVariable(value = "page", required = true) int page) {
        try {
            return Util.ok(companyService.companyMovies(id,page));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }

}
