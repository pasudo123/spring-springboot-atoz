package edu.study.pasudo123.app.user.controller;

import edu.study.pasudo123.app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public String createUser(@RequestParam(name = "name") String name){

        try{
            return userService.saveIfPossibleElseDeleteAndSave(name);
        } catch (Exception e) {
            return userService.deleteAndSave(name);
        }
    }
}
