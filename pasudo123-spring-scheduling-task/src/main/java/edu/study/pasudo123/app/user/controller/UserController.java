package edu.study.pasudo123.app.user.controller;

import edu.study.pasudo123.app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public String createUser(@RequestParam(name = "name") String name){
        return userService.saveIfPossibleElseDeleteAndSave(name);
    }
}
