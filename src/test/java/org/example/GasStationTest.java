package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class GasStationTest {

    private final GasStation gasStation = new GasStation();

    @ParameterizedTest
    @MethodSource("provideGasAndCosts")
    void shouldReturnCanCompleteCircuit(final int[] gas, final int[] costs, final int expected) {
        assertThat(gasStation.canCompleteCircuit(gas, costs), is(expected));
    }

    private static Stream<Arguments> provideGasAndCosts() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}, 3),
                Arguments.of(new int[]{2, 3, 4}, new int[]{3, 4, 3}, -1),
                Arguments.of(new int[]{2, 3, 6, 3, 1}, new int[]{2, 5, 7, 1, 1}, -1)
        );
        //@formatter:on
    }
}