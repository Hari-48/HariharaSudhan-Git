package com.example.Hari.JUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    Repo repo;

    @InjectMocks
    PlayerService playerService;

    @Test
    void getAllPlayer() {
        Player p1 = new Player(1L, "rohit", 45L);
        Player p2 = new Player(2L, "virat", 18L);

        // Creating a list of players
        List<Player> list = List.of(p1, p2);


        Mockito.when(repo.findAll()).thenReturn(list);

        assertEquals(list.get(0).getName(), playerService.getAllPlayer().get(0).getName());


    }
}