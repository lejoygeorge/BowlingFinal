package com.bnpp.bowling.parser;

import com.bnpp.bowling.model.RollSequence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCharSequenceParserTest {

    private StringCharSequenceParser parser;

    @BeforeEach
    void setUp() {
        parser = new StringCharSequenceParser();
    }

    @ParameterizedTest(name = "{index} => Sequence ''{0}'' should parse to {1}")
    @MethodSource("provideSequenceParsingScenarios")
    @DisplayName("Parse various bowling strings into their integer equivalents")
    void parse_ValidSequences_ReturnsCorrectIntegerArray(String sequence, int[] expectedRolls, String description) {
        RollSequence result = parser.parse(sequence);
        assertArrayEquals(expectedRolls, result.rolls(), "Failed parsing scenario: " + description);
    }

    @Test
    @DisplayName("Empty string should return an empty array")
    void parse_EmptyString_ReturnsEmptyArray() {
        RollSequence result = parser.parse("   ");
        assertEquals(0, result.rolls().length);
    }

    private static Stream<Arguments> provideSequenceParsingScenarios() {
        return Stream.of(
                Arguments.of(
                        "12 34 51",
                        new int[]{1, 2, 3, 4, 5, 1},
                        "Standard numbers with spaces"
                ),
                Arguments.of(
                        "-- -- --",
                        new int[]{0, 0, 0, 0, 0, 0},
                        "Gutter balls (-)"
                ),
                Arguments.of(
                        "X x X",
                        new int[]{10, 10, 10},
                        "Strikes, testing both upper and lower case X"
                ),
                Arguments.of(
                        "5/ 7/",
                        new int[]{5, 5, 7, 3},
                        "Spares (/) should calculate 10 minus the previous roll"
                ),
                Arguments.of(
                        "X 7/ 9- X -8 8/ -6 X X X81",
                        new int[]{10, 7, 3, 9, 0, 10, 0, 8, 8, 2, 0, 6, 10, 10, 10, 8, 1},
                        "Mixed complex game with bonuses"
                )
        );
    }
}
