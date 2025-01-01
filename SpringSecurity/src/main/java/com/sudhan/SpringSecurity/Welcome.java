package com.sudhan.SpringSecurity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Welcome {

    @GetMapping("/")
    public String greet(HttpServletRequest request){

        String sessionId = request.getSession().getId();
        String header = request.getHeader("username");
        String method = request.getMethod();
        return "Happy , New Year - 2025 " +"Session Id :  "+ sessionId;

    }





}
