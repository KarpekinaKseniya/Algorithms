package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class FuelTest {

    private final Fuel fuel = new Fuel();

    private static Stream<Arguments> provideRoadsAndSeats() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[][]{{0, 1}, {0, 2}, {0, 3}}, 5, 3),
                Arguments.of(new int[][]{{3, 1}, {3, 2}, {1, 0}, {0, 4}, {0, 5}, {4, 6}}, 2, 7),
                Arguments.of(new int[][]{}, 1, 0));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideRoadsAndSeats")
    void shouldReturnMinimumFuelCost(final int[][] roads, final int seats, final long expected) {
        assertThat(fuel.minimumFuelCost(roads, seats), is(expected));
    }
}