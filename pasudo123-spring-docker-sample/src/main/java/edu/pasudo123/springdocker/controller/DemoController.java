package edu.pasudo123.springdocker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class DemoController {

    @GetMapping
    public String index(){
        return "index";
    }
}
