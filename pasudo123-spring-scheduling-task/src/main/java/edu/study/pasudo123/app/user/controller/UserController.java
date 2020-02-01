package edu.study.pasudo123.app.user.controller;

import edu.study.pasudo123.app.exception.MemoryTableInsertException;
import edu.study.pasudo123.app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUser() {
        return "user";
    }

    @PostMapping
    public String createUser(@RequestParam(name = "name") String name) {

        try {
            return userService.saveIfPossibleElseDeleteAndSave(name);
        } catch (DataAccessException e) {
            return userService.deleteAndSave(name);
        }
    }
}
