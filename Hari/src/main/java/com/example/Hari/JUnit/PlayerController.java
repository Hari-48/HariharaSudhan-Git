package com.example.Hari.JUnit;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    PlayerService service;


    @GetMapping("/save")
    public Player addPlayer(@RequestBody Player cricket){
        return service.savePlayer(cricket);
    }

    @GetMapping("/get-all")
    public List<Player> getAll(){
        return service.getAllPlayer();
    }


}
