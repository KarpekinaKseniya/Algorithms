package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ShipsTest {

    private final Ships ships = new Ships();

    private static Stream<Arguments> provideWeightsAndDays() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 5, 15),
                Arguments.of(new int[] { 3, 2, 2, 4, 1, 4 }, 3, 6),
                Arguments.of(new int[] { 1, 2, 3, 1, 1 }, 4, 3));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideWeightsAndDays")
    void shouldReturnShipWithinDays(final int[] weights, final int days, final int expected) {
        assertThat(ships.shipWithinDays(weights, days), is(expected));
    }
}