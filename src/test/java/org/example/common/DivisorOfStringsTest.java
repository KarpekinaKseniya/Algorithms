package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class DivisorOfStringsTest {

    private final DivisorOfStrings divisor = new DivisorOfStrings();

    private static Stream<Arguments> provideStrings() {
        //@formatter:off
        return Stream.of(
                Arguments.of("BORING", "BORED", ""),
                Arguments.of("copy", "COPILOT", ""),
                Arguments.of("ABABAB", "ABAB", "AB"),
                Arguments.of("ABCABC", "ABC", "ABC"),
                Arguments.of("common", "word", ""));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideStrings")
    void shouldReturnGcdOfStrings(final String str1, final String str2, final String expected) {
        assertThat(divisor.gcdOfStrings(str1, str2), is(expected));
    }
}