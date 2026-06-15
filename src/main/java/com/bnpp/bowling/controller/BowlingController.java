package com.bnpp.bowling.controller;

import com.bnpp.bowling.model.ScoreRequest;
import com.bnpp.bowling.model.ScoreResponse;
import com.bnpp.bowling.service.BowlingScoringService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bowling")
@RequiredArgsConstructor
public class BowlingController {

    private final BowlingScoringService scoringService;

    @PostMapping("/score")
    public ResponseEntity<ScoreResponse> calculateGameScore(@Valid @RequestBody ScoreRequest request) {
        var sequence = request.sequence();
        var totalScore = scoringService.calculateScore(sequence);
        return ResponseEntity.ok(new ScoreResponse(totalScore, sequence));
    }
}
