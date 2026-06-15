package com.bnpp.bowling.service;

import com.bnpp.bowling.parser.SequenceParser;
import com.bnpp.bowling.parser.StringCharSequenceParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TenPinScoringServiceTest {

    private SequenceParser parser = new StringCharSequenceParser();

    private TenPinScoringService scoringService = new TenPinScoringService(parser);

    @Test
    @DisplayName("Gutter game should score 0")
    void testAllMisses() {
        assertEquals(0, scoringService.calculateScore("--------------------"));
    }

    @Test
    @DisplayName("All ones should score 20")
    void testAllOnes() {
        assertEquals(20, scoringService.calculateScore("11111111111111111111"));
    }

    @Test
    @DisplayName("Pairs of 9 and miss should score 90")
    void testPairsOfNineAndMiss() {
        assertEquals(90, scoringService.calculateScore("9-9-9-9-9-9-9-9-9-9-"));
    }

    @Test
    @DisplayName("Pairs of 1 and miss with space should score 10")
    void testPairsOfOneAndMissWithSpace() {
        assertEquals(10, scoringService.calculateScore("1- 1- 1- 1- 1- 1- 1- 1- 1- 1-"));
    }

    @Test
    @DisplayName("One Spare then misses should score 16")
    void testOneSpareAndThenAllMisses() {
        assertEquals(16, scoringService.calculateScore("5/ 3- -- -- -- -- -- -- -- --"));
    }

    @Test
    @DisplayName("All Spares with a final 5 should score 150")
    void testAllSpareWithFinalFive() {
        assertEquals(150, scoringService.calculateScore("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"));
    }

    @Test
    @DisplayName("One Strike then misses should score 24")
    void testOneStrikeAndThenAllMisses() {
        assertEquals(24, scoringService.calculateScore("X 34 -- -- -- -- -- -- -- --"));
    }
}