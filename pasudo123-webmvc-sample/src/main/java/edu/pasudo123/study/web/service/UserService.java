package edu.pasudo123.study.web.service;

import edu.pasudo123.study.web.dao.UserDao;
import edu.pasudo123.study.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User create(String name, int age, String gender){
        return userDao.create(name, age, gender);
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findById(int id){
        return userDao.findById(id);
    }

    public List<User> findByName(String name){
        return userDao.findByName(name);
    }

    public List<User> findByAge(int age){
        return userDao.findByAge(age);
    }
}
