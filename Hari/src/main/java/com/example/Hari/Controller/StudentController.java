package com.example.Hari.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {


    @GetMapping("/greet")
    public String greet(){
        return "Hi, Good Morning";
    }
}
