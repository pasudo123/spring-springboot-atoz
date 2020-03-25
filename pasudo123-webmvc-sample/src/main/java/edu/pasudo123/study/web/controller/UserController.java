package edu.pasudo123.study.web.controller;

import edu.pasudo123.study.web.model.User;
import edu.pasudo123.study.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService){
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestParam("name") String name,
                           @RequestParam("age") Integer age,
                           @RequestParam("gender") String gender){
        return userService.create(name,age, gender);
    }
}