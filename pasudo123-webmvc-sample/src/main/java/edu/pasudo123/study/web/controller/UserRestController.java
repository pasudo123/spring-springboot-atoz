package edu.pasudo123.study.web.controller;

import edu.pasudo123.study.web.model.User;
import edu.pasudo123.study.web.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v2")
public class UserRestController {

    private final UserService userService;

    public UserRestController(final UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "user/{id}")
    public User findById(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @PostMapping(value = "/user")
    public User createUser(@RequestParam("name") String name,
                           @RequestParam("age") Integer age,
                           @RequestParam("gender") String gender){
        return userService.create(name,age, gender);
    }
}
