package com.hrs.hotelreservationsystem.dao;

import com.hrs.hotelreservationsystem.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {


    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);
}

