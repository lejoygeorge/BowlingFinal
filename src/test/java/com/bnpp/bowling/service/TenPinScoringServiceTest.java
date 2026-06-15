package com.bnpp.bowling.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TenPinScoringServiceTest {

    private final TenPinScoringService service = new TenPinScoringService();

    @Test
    @DisplayName("Gutter game should score 0")
    void testAllMisses() {
        assertEquals(0, service.calculateScore("--------------------"));
    }

    @Test
    @DisplayName("All ones should score 20")
    void testAllOnes() {
        assertEquals(20, service.calculateScore("11111111111111111111"));
    }
}