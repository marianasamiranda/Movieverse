package controller;

import log.LogMethod;
import business.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class MembersController {

    @Autowired
    MemberService memberService;


    @LogMethod
    @RequestMapping(method = GET, value = "/people-search")
    public ResponseEntity<Object> membersSearch(@RequestParam(value = "name") String name) {
        try {
            return Util.ok(memberService.search(name));
        }
        catch (Exception e) {
            return Util.badRequest("");
        }
    }


    @RequestMapping(method = GET, value = "/member/{id}")
    public ResponseEntity<Object> profile(@PathVariable(value = "id", required = true) int id) {
        try {
            return Util.ok(memberService.memberInfo(id));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }


    @RequestMapping(method = GET, value = "/member-movies/{id}/{page}")
    public ResponseEntity<Object> memberMovies(@PathVariable(value = "id", required = true) int id, @PathVariable(value = "page", required = true) int page) {
        try {
            return Util.ok(memberService.memberMovies(id,page));
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }


    @RequestMapping(method = GET, value = "/people-search-page")
    public ResponseEntity<Object> memberSearchPage() {
        try {
            return Util.ok(memberService.memberSearchPage());
        }
        catch (Exception e) {
            return Util.badRequest(e.getMessage());
        }
    }
}
