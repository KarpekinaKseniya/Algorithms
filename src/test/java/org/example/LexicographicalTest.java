package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class LexicographicalTest {

    private final Lexicographical lexicographical = new Lexicographical();

    @ParameterizedTest
    @CsvSource(value = {
            "parker:morris:parser:makkek",
            "hello:world:hold:hdld",
            "leetcode:programs:sourcecode:aauaaaaada"}, delimiter = ':')
    void shouldReturnSmallestEquivalentString(final String s1, final String s2, final String baseStr, final String expected) {
        assertThat(lexicographical.smallestEquivalentString(s1, s2, baseStr), is(expected));
    }
}
