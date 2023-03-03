package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AnagramsTest {

    private final Anagrams anagrams = new Anagrams();

    private static Stream<Arguments> provideStringsAndResult() {
        //@formatter:off
        return Stream.of(
                Arguments.of("cbaebabacd", "abc", List.of(0, 6)),
                Arguments.of("abab", "ab", List.of(0, 1, 2)),
                Arguments.of("alerts", "talers", List.of(0)));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideStringsAndResult")
    void shouldFindAnagrams(final String s, final String p, final List<Integer> expected) {
        assertThat(anagrams.findAnagrams(s, p), is(expected));
    }
}