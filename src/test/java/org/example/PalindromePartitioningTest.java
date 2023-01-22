package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class PalindromePartitioningTest {

    private final PalindromePartitioning palindrome = new PalindromePartitioning();

    private static Stream<Arguments> provideWordAndPartitions() {
        //@formatter:off
        return Stream.of(
                Arguments.of("aab", List.of(List.of("a", "a", "b"), List.of("aa", "b"))),
                Arguments.of("z", List.of(List.of("z"))),
                Arguments.of("bool", List.of(List.of("b", "o", "o", "l"), List.of("b", "oo", "l")))
        );
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideWordAndPartitions")
    void shouldReturnPartition(final String str, final List<List<String>> expected) {
        assertThat(palindrome.partition(str), is(expected));
    }
}