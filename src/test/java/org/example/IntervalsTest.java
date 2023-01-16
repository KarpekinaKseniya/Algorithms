package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class IntervalsTest {

    private final Intervals interval = new Intervals();

    @ParameterizedTest
    @MethodSource("provideIntervals")
    void shouldReturnIntervalsAfterInsert(final int[][] intervals, final int[] newInterval, final int[][] expected) {
        assertThat(interval.insert(intervals, newInterval), is(expected));
    }

    private static Stream<Arguments> provideIntervals() {
        //@formatter:off
        return Stream.of(
                Arguments.of(
                        new int[][]{{1, 3}, {6, 9}},
                        new int[]{2, 5},
                        new int[][]{{1, 5}, {6, 9}}),
                Arguments.of(
                        new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}},
                        new int[]{4, 8},
                        new int[][]{{1, 2}, {3, 10}, {12, 16}})
        );
        //@formatter:on
    }
}