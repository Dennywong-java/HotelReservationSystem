package com.hrs.hotelreservationsystem.controller;

import com.hrs.hotelreservationsystem.pojo.User;
import com.hrs.hotelreservationsystem.pojo.requestbody.UserRequest;
import com.hrs.hotelreservationsystem.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

//    // Index page
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index() {
//        return "Hello! This is the User Index Page!";
//    }

    // Get All user
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> index() {
        return userServiceImpl.getAllUser();
    }

    // Sign up user
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User signup(@RequestBody UserRequest userRequest) {
        return userServiceImpl.createUser(userRequest);
    }

    // Get user by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getById(@PathVariable(value = "id") Integer id) {
        return userServiceImpl.getById(id);
    }

    // Update user by id
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User updateById(@PathVariable(value = "id") Integer id, @RequestBody UserRequest userRequest) {
        System.out.println("Updated User: " + id);
        return userServiceImpl.updateById(id, userRequest);
    }

    // Delete user by id
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable(value = "id") Integer id) {
        userServiceImpl.deleteById(id);
        return "User deleted successfully!";
    }

}
