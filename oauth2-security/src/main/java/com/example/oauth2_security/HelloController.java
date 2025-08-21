package com.example.oauth2_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint.";
    }

    @GetMapping("/secure")
    public String secureEndpoint(Principal principal) {
        return "Hello, " + principal.getName() + ". You are authenticated!";
    }
}