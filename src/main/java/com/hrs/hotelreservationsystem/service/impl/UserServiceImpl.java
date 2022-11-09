package com.hrs.hotelreservationsystem.service.impl;

import com.hrs.hotelreservationsystem.dao.UserRepository;
import com.hrs.hotelreservationsystem.exception.myException;
import com.hrs.hotelreservationsystem.pojo.User;
import com.hrs.hotelreservationsystem.pojo.requestbody.UserRequest;
import com.hrs.hotelreservationsystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger("User Service Loggers");

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserRequest userRequest) {
        User newUser = new User();
        newUser.setName(userRequest.getName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPhone(userRequest.getPhone());
        Double id = Math.random() * 10000;
        Integer integer = id.intValue();
        newUser.setId(integer);
        User save = userRepository.save(newUser);
        return save;
    }

    @Override
    public User getById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else throw new myException("User: " + id + " not found!");
    }

    @Override
    public User updateById(Integer id, UserRequest userRequest) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userRequest.getName());
            user.setEmail(userRequest.getEmail());
            user.setPhone(userRequest.getPhone());
            return userRepository.save(user);
        } else throw new myException("User: " + id + "Not found!");
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
