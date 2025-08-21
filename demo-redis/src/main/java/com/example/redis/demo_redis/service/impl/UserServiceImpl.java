package com.example.redis.demo_redis.service.impl;

import com.example.redis.demo_redis.model.User;
import com.example.redis.demo_redis.model.UserDto;
import com.example.redis.demo_redis.repository.UserRepository;
import com.example.redis.demo_redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(UserDto user) {
        return userRepository.save(User.builder().email(user.getEmail()).name(user.getName()).build());
    }

    @Override
    @Cacheable(value = "allUsers")
    public List<User> getAllUsersWithCache() {
        System.out.println("Fetching all users from DB (with cache)");
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsersWithoutCache() {
        return userRepository.findAll();
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
