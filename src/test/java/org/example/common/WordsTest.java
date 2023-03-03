package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class WordsTest {

    private final Words words = new Words();

    private static Stream<Arguments> provideConcatenatedWords() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"}, List.of("catsdogcats", "dogcatsdog", "ratcatdogcat")),
                Arguments.of(new String[]{"cat", "dog", "catdog"}, List.of("catdog")),
                Arguments.of(new String[]{"apple", "pine", "pineapple", "carrot"}, List.of("pineapple"))
        );
        //@formatter:on
    }

    private static Stream<Arguments> provideWordsAndString() {
        //@formatter:off
        return Stream.of(
                Arguments.of("barfoothefoobarman", new String[] { "foo", "bar" }, List.of(0, 9)),
                Arguments.of("wordgoodgoodgoodbestword", new String[] { "word", "good", "best", "word" }, emptyList()),
                Arguments.of("barfoofoobarthefoobarman", new String[] { "bar", "foo", "the" }, List.of(6, 9, 12)));
        //@formatter:on
    }

    @ParameterizedTest
    //@formatter:off
    @CsvSource(value = {"abba:dog cat cat dog:true",
            "abba:dog cat cat fish:false",
            "abba:cat cat cat cat:false",
            "aaaa:dog cat cat dog:false",
            "aba:dog cat cat dog:false",
            "aba:dog cat cat:false"}, delimiter = ':')
    //@formatter:on
    void shouldCheckWordPattern(final String pattern, final String s, final boolean expected) {
        assertThat(words.wordPattern(pattern, s), is(expected));
    }

    @ParameterizedTest
    @CsvSource(value = { "USA:true", "FlAg:false", "boring:true", "g:true", "Google:true",
            "fLaG:false" }, delimiter = ':')
    void shouldDetectCapitalUse(final String word, final boolean expected) {
        assertThat(words.detectCapitalUse(word), is(expected));
    }

    @ParameterizedTest
    @MethodSource("provideConcatenatedWords")
    void shouldReturnConcatenatedWords(final String[] arrayOfWords, List<String> expected) {
        assertThat(words.findAllConcatenatedWordsInADict(arrayOfWords), is(expected));
    }

    @ParameterizedTest
    @MethodSource("provideWordsAndString")
    void shouldFindSubstringIndexes(final String s, final String[] arrayOfWords, final List<Integer> expected) {
        assertThat(words.findSubstring(s, arrayOfWords), is(expected));
    }
}