package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class LongestSubstringTest {

    private final LongestSubstring longestSubstring = new LongestSubstring();

    @ParameterizedTest
    @MethodSource("provideStringAndResult")
    void shouldFindLengthOfLongestSubstring(final String string, final int expected) {
        assertThat(longestSubstring.findLengthOfLongestSubstring(string), is(expected));
    }

    private static Stream<Arguments> provideStringAndResult() {
        //@formatter:off
        return Stream.of(
                Arguments.of("au", 2),
                Arguments.of("dvdf", 3),
                Arguments.of("b", 1),
                Arguments.of("abcabcbb", 3),
                Arguments.of("pwwkew", 3),
                Arguments.of("anviaj", 5),
                Arguments.of("ohomm", 3),
                Arguments.of("", 0),
                Arguments.of(null, -1),
                Arguments.of("t", 1)
        );
        //@formatter:on
    }
}