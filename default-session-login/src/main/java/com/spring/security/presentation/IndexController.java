package com.spring.security.presentation;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "/")
    public Authentication index(Authentication authentication) {
        return authentication;
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "user";
    }
}
