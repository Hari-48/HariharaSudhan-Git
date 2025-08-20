package com.example.Hari.JUnit;


import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    Repo repo;


    public Player savePlayer(Player cricket){
       return repo.save(cricket);
    }

    public List<Player> getAllPlayer() {
        return repo.findAll();
    }
}
