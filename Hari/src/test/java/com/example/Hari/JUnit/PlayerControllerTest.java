package com.example.Hari.JUnit;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    void getAll() throws Exception {
        // Creating sample player objects
        Player p1 = new Player(1L, "rohit", 45L);
        Player p2 = new Player(2L, "virat", 18L);

        // Creating a list of players
        List<Player> list = List.of(p1, p2);

        // Mocking the service method to return the player list
        Mockito.when(playerService.getAllPlayer()).thenReturn(list);

        // Performing GET request and verifying the response status and content
        mockMvc.perform(MockMvcRequestBuilders.get("/get-all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("rohit"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("virat"));

    }

}