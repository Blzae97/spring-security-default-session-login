package com.spring.security.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index() {
        return "login";
    }

    @GetMapping(value = "/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping(value = "/user")
    public String user(){
        return "user";
    }
}
