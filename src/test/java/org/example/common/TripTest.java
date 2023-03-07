package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class TripTest {

    private final Trip trip = new Trip();

    private static Stream<Arguments> provideTimeAndTotalTrips() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, 5, 3),
                Arguments.of(new int[]{2}, 1, 2),
                Arguments.of(new int[]{1, 2, 3, 7, 9, 5}, 4, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTimeAndTotalTrips")
    void minimumTime(final int[] time, final int totalTrips, final long expected) {
        assertThat(trip.minimumTime(time, totalTrips), is(expected));
    }
}