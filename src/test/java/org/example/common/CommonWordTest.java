package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CommonWordTest {

    private final CommonWord commonWord = new CommonWord();

    @ParameterizedTest
    @MethodSource("provideRequestAndExpectedValues")
    void shouldReturnMostCommonWord(final String paragraph, final String[] banned, final String expected) {
        assertThat(commonWord.mostCommonWord(paragraph, banned), is(expected));
    }

    private static Stream<Arguments> provideRequestAndExpectedValues() {
        //@formatter:off
        return Stream.of(
                Arguments.of("a, a, a, a, b,b,b,c, c", new String[]{"a"}, "b"),
                Arguments.of("Bob. hIt, baLl", new String[]{"bob", "hit"}, "ball"),
                Arguments.of("Bob. hIt, baLl", new String[]{"bob", "hit", "ball"}, null),
                Arguments.of("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}, "ball"),
                Arguments.of("a.", new String[]{}, "a")
        );
        //@formatter:on
    }
}