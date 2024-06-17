package com.spring.security.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestIndexController {

    @GetMapping(value = "/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping(value = "/denied")
    public String denied(@RequestParam(value = "exception") String exceptionMessage){
        return exceptionMessage;
    }
}
