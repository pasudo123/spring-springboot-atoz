package edu.pasudo123.study.web.dao;

import edu.pasudo123.study.web.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class UserDao {

    private final AtomicInteger atomicInteger = new AtomicInteger(1);
    private final Set<User> userStorage = new HashSet<>();

    @PostConstruct
    public void init(){
        userStorage.add(new User(generateId(), "PARK", 100, User.Gender.MAN));
        userStorage.add(new User(generateId(), "LEE", 120, User.Gender.MAN));
        userStorage.add(new User(generateId(), "JE", 125, User.Gender.WOMAN));
        userStorage.add(new User(generateId(), "SIN", 70 , User.Gender.MAN));
        userStorage.add(new User(generateId(), "CHA", 30, User.Gender.WOMAN));
    }

    private int generateId(){
        return atomicInteger.getAndIncrement();
    }

    public User create(String name, int age, String gender){
        User user = new User(generateId(), name, age, User.Gender.valueOf(gender));
        userStorage.add(user);
        return user;
    }

    public List<User> findAll(){
        return new ArrayList<>(userStorage);
    }

    public User findById(int id){
        return (new ArrayList<>(userStorage)).stream()
                .filter(user -> user.isEqualId(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Id, %s 를 가진 유저는 존재하지 않습니다.", id)));
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
