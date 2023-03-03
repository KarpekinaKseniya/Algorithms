package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class DuplicateCharactersTest {

    private final DuplicateCharacters duplicateChar = new DuplicateCharacters();

    @ParameterizedTest
    @MethodSource("provideInputAndExpectedValue")
    void findDuplicateChar(final String word, final char[] expected) {
        assertThat(duplicateChar.findDuplicateChar(word), is(expected));
    }

    private static Stream<Arguments> provideInputAndExpectedValue() {
        //@formatter:off
        return Stream.of(
                Arguments.of("Programming", new char[]{'r', 'g', 'm'}),
                Arguments.of("Java", new char[]{'a'}),
                Arguments.of("Easy", null)
        );
        //@formatter:on
    }
}