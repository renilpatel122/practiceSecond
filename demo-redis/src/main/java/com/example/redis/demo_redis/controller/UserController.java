package com.example.redis.demo_redis.controller;

import com.example.redis.demo_redis.model.User;
import com.example.redis.demo_redis.model.UserDto;
import com.example.redis.demo_redis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/users")
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

//    @PostMapping
//    public User addUser(@RequestBody User user) {
//        return userService.addUser(user);
//    }
//
//    @GetMapping("/all/nocache")
//    public List<User> getAllUsersWithoutCache() {
//        return userService.getAllUsersWithoutCache();
//    }
//
//    @GetMapping("/all/cache")
//    public List<User> getAllUsersWithCache() {
//        return userService.getAllUsersWithCache();
//    }

    @QueryMapping
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userService.getAllUsersWithoutCache();
        logger.info("Retrieved {} users", users.size());
        return users;
    }
    @QueryMapping
    public User getUserById(@Argument Long id) {
        logger.info("Fetching user with id: {}", id);
        User user = userService.getUser(id);
        if (user != null) {
            logger.info("User found with id: {}", id);
        } else {
            logger.warn("No user found with id: {}", id);
        }
        return user;
    }

    @MutationMapping
    public User createUser(@Argument UserDto user) {
        logger.info("Creating new user: {}", user);
        User createdUser = userService.addUser(user);
        logger.info("User created successfully with id: {}", createdUser.getId());
        return createdUser;
    }

}

