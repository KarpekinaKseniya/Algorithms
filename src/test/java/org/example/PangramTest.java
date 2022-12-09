package org.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PangramTest {

    private final Pangram pangram = new Pangram();

    @ParameterizedTest
    @MethodSource("provideSentenceAndResult")
    void shouldCheckIfPangram(final String sentence, final boolean expected) {
        assertThat(pangram.checkIfPangram(sentence), is(expected));
    }

    private static Stream<Arguments> provideSentenceAndResult() {
        //@formatter:off
        return Stream.of(
                Arguments.of("Pack my box with five dozen liquor jugs.", true),
                Arguments.of("thequickbrownfoxjumpsoverthelazydog", true),
                Arguments.of("solution", false),
                Arguments.of("Mr. Jock, TV quiz PhD.", false),
                Arguments.of("Waltz, nymph", false));
        //@formatter:on
    }
}