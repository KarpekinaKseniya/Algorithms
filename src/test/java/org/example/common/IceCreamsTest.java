package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class IceCreamsTest {

    private final IceCreams iceCreams = new IceCreams();

    @ParameterizedTest
    @MethodSource("provideIceCostAndMaxBought")
    void shouldReturnMaxIceCream(final int[] costs, final int coins, final int max) {
        assertThat(iceCreams.maxIceCream(costs, coins), is(max));
    }

    private static Stream<Arguments> provideIceCostAndMaxBought() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[] { 1, 3, 2, 4, 1 }, 7, 4),
                Arguments.of(new int[] { 10, 6, 8, 7, 7, 8 }, 5, 0),
                Arguments.of(new int[] { 1, 6, 3, 1, 2, 5 }, 20, 6));
        //@formatter:on
    }
}