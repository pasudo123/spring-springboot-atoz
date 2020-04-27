package edu.pasudo123.gracefully.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @RequestMapping("/sample")
    public String sample() throws InterruptedException {
        Thread.sleep(5000);
        return "Process finished";
    }
}
