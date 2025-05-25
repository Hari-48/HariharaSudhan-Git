package com.example.Hari.RabbitMq.controller;


import com.example.Hari.RabbitMq.service.RabbitMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/rabbit")
public class RabbitMqController {

    @Autowired
    private RabbitMqProducer producer;

    @PostMapping("/send")
    public String send(@RequestParam String message) {
        producer.sendMessage(message);
        return "Message sent!";
    }

    @GetMapping("/send")
    public String generateMessage() throws InterruptedException {
        int num = 5;
        String message = LocalDateTime.now().toString();
        while (num > 0) {
            message = "Time is" + LocalDateTime.now();
            producer.sendMessage(message);
            Thread.sleep(10000);
            num--;
        }

        return "Message sent!";
    }


}
