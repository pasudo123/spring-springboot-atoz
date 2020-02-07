package edu.study.pasudo123.redissample.controller;

import edu.study.pasudo123.redissample.atomics.MonitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisSampleController {

    private final MonitorRepository monitorRepository;

    @GetMapping
    public String get(){



        return "";
    }

    @PostMapping
    public String post() {
        return "";
    }

}
