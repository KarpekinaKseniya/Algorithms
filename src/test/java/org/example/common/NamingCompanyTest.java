package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class NamingCompanyTest {

    private final NamingCompany company = new NamingCompany();

    private static Stream<Arguments> provideIdeas() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new String[]{"coffee", "donuts", "time", "toffee"}, 6),
                Arguments.of(new String[]{"lack", "back"}, 0),
                Arguments.of(new String[]{"bored", "bad", "result"}, 4));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideIdeas")
    void shouldReturnNumberOfDistinctNames(final String[] ideas, final long expected) {
        assertThat(company.distinctNames(ideas), is(expected));
    }
}