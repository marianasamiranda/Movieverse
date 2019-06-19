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
    @RequestMapping(method = GET, value = "/member/search")
    public ResponseEntity<Object> membersSearch(@RequestParam(value = "name") String name) {
        return Util.callServiceAndReturn(() -> memberService.search(name));
    }


    @RequestMapping(method = GET, value = "/member/{id}")
    public ResponseEntity<Object> profile(@PathVariable(value = "id") int id) {
        return Util.callServiceAndReturn(() -> memberService.memberInfo(id));
    }


    @RequestMapping(method = GET, value = "/member/{id}/movies")
    public ResponseEntity<Object> memberMovies(@PathVariable(value = "id") int id,
                                               @RequestParam(value = "page") int page) {
        return Util.callServiceAndReturn(() -> memberService.memberMovies(id, page));
    }


    @RequestMapping(method = GET, value = "/member/search-page")
    public ResponseEntity<Object> memberSearchPage() {
        return Util.callServiceAndReturn(() -> memberService.memberSearchPage());
    }
}
