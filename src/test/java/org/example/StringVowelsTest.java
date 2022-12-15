package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class StringVowelsTest {

    private final StringVowels vowels = new StringVowels();

    @ParameterizedTest
    @CsvSource(value = {"1:5", "2:15", "33:66045", "50:316251"}, delimiter = ':')
    void shouldCountVowelStrings(final int n, final int expected) {
        assertThat(vowels.countVowelStrings(n), is(expected));
    }
}