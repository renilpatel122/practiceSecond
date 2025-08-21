//package com.example.redis.demo_redis.resolver;
//
//import com.example.redis.demo_redis.model.User;
//import com.example.redis.demo_redis.service.UserService;
//import graphql.kickstart.tools.GraphQLQueryResolver;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class UserQueryResolver implements GraphQLQueryResolver {
//    private final UserService userService;
//
//    public User getUserById(Long id) {
//        return userService.getUser(id);
//    }
//}
