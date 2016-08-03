package net.chandol.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// FIXME 삭제할 것
@Controller
public class SampleController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}