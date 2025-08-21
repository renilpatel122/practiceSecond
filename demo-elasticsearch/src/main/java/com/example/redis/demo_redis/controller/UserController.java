//package com.example.redis.demo_redis.controller;

//import com.example.redis.demo_redis.model.User;
//import com.example.redis.demo_redis.model.UserDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

//@RestController
//@RequestMapping("/api/users")
//@Controller
//public class UserController {

//    @Autowired
//    private UserService userService;

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

//    @QueryMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsersWithoutCache();
//    }
//
//    @QueryMapping
//    public User getUserById(@Argument Long id) {
//        return userService.getUser(id);
//    }
//
//    @MutationMapping
//    public User createUser(@Argument UserDto user) {
//        return userService.addUser(user);
//    }

//}
