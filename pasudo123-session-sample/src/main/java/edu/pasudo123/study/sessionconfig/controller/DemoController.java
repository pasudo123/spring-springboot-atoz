package edu.pasudo123.study.sessionconfig.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class DemoController {

    @GetMapping("/")
    public String root() {
        return "root";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/session-count")
    public String sessionCount(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if(session == null) {
            log.info("Unable to find Session. Creating a new Session.");
            session = request.getSession(true);
        }
        return "Session Test Complete";
    }

}
