package com.bnpp.bowling.service;

import com.bnpp.bowling.parser.SequenceParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bnpp.bowling.constants.BowlingConstants.*;
@Service
@RequiredArgsConstructor
public class TenPinScoringService implements BowlingScoringService{

    private final SequenceParser parser;

    public int calculateScore(String sequence) {
        var parsedSequence = parser.parse(sequence);
        var rolls = parsedSequence.rolls();
        int score = 0;
        int rollIndex = 0;
        for (int frame = 0; frame < FRAMESIZE; frame++) {
            if (isStrike(rolls, rollIndex)) {
                score += 10 + strikeBonus(rolls, rollIndex);
                rollIndex += ONEROLL;
            } else if (isSpare(rolls, rollIndex)) {
                score += MAXPOINT + spareBonus(rolls, rollIndex);
                rollIndex += TWOROLL;
            } else {
                score += sumOfPinsInFrame(rolls, rollIndex);
                rollIndex += TWOROLL;
            }
        }
        return score;
    }

    private boolean isStrike(int[] rolls, int rollIndex) {
        return rolls[rollIndex] == 10;
    }

    private int strikeBonus(int[] rolls, int rollIndex) {
        return rolls[rollIndex + 1] + rolls[rollIndex + 2];
    }

    private boolean isSpare(int[] rolls, int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1] == 10;
    }

    private int spareBonus(int[] rolls, int rollIndex) {
        return rolls[rollIndex + 2];
    }

    private int sumOfPinsInFrame(int[] rolls, int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1];
    }
}
