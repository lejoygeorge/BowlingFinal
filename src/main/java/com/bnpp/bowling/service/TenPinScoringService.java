package com.bnpp.bowling.service;

public class TenPinScoringService {

    public int calculateScore(String sequence) {
        int[] rolls = parseSequence(sequence);
        int score = 0;
        int rollIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            score += rolls[rollIndex] + rolls[rollIndex + 1];
            rollIndex += 2;
        }
        return score;
    }

    private int[] parseSequence(String seq) {
        int[] rolls = new int[seq.length()];

        for (int i = 0; i < seq.length(); i++) {
            char c = seq.charAt(i);
            rolls[i] = switch (c) {
                case '-' -> 0;
                default -> Character.getNumericValue(c);
            };
        }
        return rolls;
    }
}
