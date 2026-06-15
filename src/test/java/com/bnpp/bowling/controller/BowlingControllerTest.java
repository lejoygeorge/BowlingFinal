package com.bnpp.bowling.controller;

import com.bnpp.bowling.model.ScoreRequest;
import com.bnpp.bowling.service.BowlingScoringService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BowlingController.class)
class BowlingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BowlingScoringService scoringService;

    @Test
    @DisplayName("POST /score with valid sequence should return 200 OK and the calculated score")
    void calculateGameScore_ValidRequest_ReturnsOkWithScore() throws Exception {
        String validSequence = "X X X X X X X X X X X X";
        ScoreRequest request = new ScoreRequest(validSequence);
        int expectedScore = 300;
        when(scoringService.calculateScore(validSequence)).thenReturn(expectedScore);
        mockMvc.perform(post("/api/v1/bowling/score")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value(expectedScore))
                .andExpect(jsonPath("$.sequence").value(validSequence));
    }

    @Test
    @DisplayName("POST /score with empty sequence should trigger validation and return 400 Bad Request")
    void calculateGameScore_EmptySequence_ReturnsBadRequest() throws Exception {
        ScoreRequest request = new ScoreRequest("");
        mockMvc.perform(post("/api/v1/bowling/score")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /score with invalid characters should trigger validation and return 400 Bad Request")
    void calculateGameScore_InvalidCharacters_ReturnsBadRequest() throws Exception {
        ScoreRequest request = new ScoreRequest("X 5/ ABC");
        mockMvc.perform(post("/api/v1/bowling/score")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
