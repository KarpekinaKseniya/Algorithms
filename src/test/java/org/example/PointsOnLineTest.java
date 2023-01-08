package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class PointsOnLineTest {

    private final PointsOnLine pointsOnLine = new PointsOnLine();

    @ParameterizedTest
    @MethodSource("providePointsAndMaxPoints")
    void shouldReturnMaxPoints(final int[][] points, final int expected) {
        assertThat(pointsOnLine.maxPoints(points), is(expected));
    }

    private static Stream<Arguments> providePointsAndMaxPoints() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[][]{{1, 1}, {2, 2}, {3, 3}}, 3),
                Arguments.of(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}, 4),
                Arguments.of(new int[][]{{2, 9}, {6, 7}}, 2)
        );
        //@formatter:on
    }
}