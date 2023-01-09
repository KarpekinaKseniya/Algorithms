package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CirclesTest {

    private final Circles circles = new Circles();

    @ParameterizedTest
    @MethodSource("providePoints")
    void shouldCountPoints(final int[][] points, final int[][] queries, final int[] expected) {
        assertThat(circles.countPoints(points, queries), is(expected));
    }

    private static Stream<Arguments> providePoints() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[][]{{1, 3}, {3, 3}, {5, 3}, {2, 2}},
                        new int[][]{{2, 3, 1}, {4, 3, 1}, {1, 1, 2}},
                        new int[]{3, 2, 2}),
                Arguments.of(new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}},
                        new int[][]{{1, 2, 2}, {2, 2, 2}, {4, 3 ,2}, {4, 3, 3}},
                        new int[]{2, 3, 2, 4})
        );
        //@formatter:on
    }
}