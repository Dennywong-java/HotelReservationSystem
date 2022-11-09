package com.hrs.hotelreservationsystem.service;

import com.hrs.hotelreservationsystem.pojo.User;
import com.hrs.hotelreservationsystem.pojo.requestbody.UserRequest;

import java.util.List;


public interface UserService {
    // create user
    User createUser(UserRequest userRequest);

    User getById(Integer id);

    User updateById(Integer id, UserRequest userRequest);

    void deleteById(Integer id);

    List<User> getAllUser();

}
