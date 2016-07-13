package net.chandol.study.ticketing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketingViewController {

    // 영화와 영화관 그리고 시간을 고른다.
    @GetMapping("/ticketing")
    public String index() {
        return "ticketing/index";
    }
}
