package com.example.Hari.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    // Front controller - see all the request mapping and sent to corresponding controller
    @RequestMapping("/")
    @ResponseBody
    public String greet() {
        return "welcome to SPRING BOOT";
    }

    @ResponseBody
    @RequestMapping("about")
    public String about(){
        return " we don't teach , we educate";
    }


}
