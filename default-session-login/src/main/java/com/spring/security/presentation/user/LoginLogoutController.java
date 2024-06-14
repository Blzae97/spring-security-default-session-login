package com.spring.security.presentation.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginLogoutController {
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
