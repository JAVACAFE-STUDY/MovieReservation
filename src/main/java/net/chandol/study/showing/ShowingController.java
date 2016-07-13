package net.chandol.study.showing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowingController {

    @Autowired Showing.ShowingRepository repository;

    @GetMapping("/showing")
    public String showing(){
        return "showing";
    }
}
