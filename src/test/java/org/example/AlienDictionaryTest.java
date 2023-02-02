package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AlienDictionaryTest {

    private final AlienDictionary dictionary = new AlienDictionary();

    private static Stream<Arguments> provideWordsAndOrder() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz", true),
                Arguments.of(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz", false),
                Arguments.of(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz", false));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideWordsAndOrder")
    void shouldCheckIsAlienSorted(final String[] words, final String order, final boolean expected) {
        assertThat(dictionary.isAlienSorted(words, order), is(expected));
    }
}