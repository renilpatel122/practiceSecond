package com.food.user.dto;

import com.food.user.entity.Role;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    private Role role;
}
