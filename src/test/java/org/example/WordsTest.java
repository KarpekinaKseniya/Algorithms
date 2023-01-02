package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class WordsTest {

    private final Words words = new Words();

    @ParameterizedTest
    @CsvSource(value = {"abba:dog cat cat dog:true",
            "abba:dog cat cat fish:false",
            "abba:cat cat cat cat:false",
            "aaaa:dog cat cat dog:false",
            "aba:dog cat cat dog:false",
            "aba:dog cat cat:false"}, delimiter = ':')
    void shouldCheckWordPattern(final String pattern, final String s, final boolean expected) {
        assertThat(words.wordPattern(pattern, s), is(expected));
    }

    @ParameterizedTest
    @CsvSource(value = {"USA:true", "FlAg:false", "boring:true", "g:true", "Google:true"}, delimiter = ':')
    void shouldDetectCapitalUse(final String word, final boolean expected) {
        assertThat(words.detectCapitalUse(word), is(expected));
    }
}