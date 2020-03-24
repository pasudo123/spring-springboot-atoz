package edu.pasudo123.study.web.service;

import edu.pasudo123.study.web.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final Set<User> userStorage = new HashSet<>();
    private static final String EXCEPTION_FORMAT_STR = "Id %s 를 가진 유저는 존재하지 않습니다.";

    @PostConstruct
    public void init(){
        userStorage.add(new User(1, "PARK", 100, User.Gender.MAN));
        userStorage.add(new User(2, "LEE", 120, User.Gender.MAN));
        userStorage.add(new User(3, "JE", 125, User.Gender.WOMAN));
        userStorage.add(new User(4, "SIN", 70 , User.Gender.MAN));
        userStorage.add(new User(5, "CHA", 30, User.Gender.WOMAN));
    }

    public List<User> findAll(){
        return new ArrayList<>(userStorage);
    }

    public User findById(int id){
        return (new ArrayList<>(userStorage)).stream()
                .filter(user -> user.isEqualId(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(EXCEPTION_FORMAT_STR, id)));
    }

    public List<User> findByName(String name){
        return (new ArrayList<>(userStorage)).stream()
                .filter(user -> user.isEqualName(name))
                .collect(Collectors.toList());
    }

    public List<User> findByAge(int age){
        return (new ArrayList<>(userStorage)).stream()
                .filter(user -> user.isEqualAge(age))
                .collect(Collectors.toList());
    }
}
