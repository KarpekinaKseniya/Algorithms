package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class FarLandTest {

    private final FarLand farLand = new FarLand();

    private static Stream<Arguments> provideGridAndMaxDist() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}, 2),
                Arguments.of(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}}, 4),
                Arguments.of(new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 1, 1}}, 1));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideGridAndMaxDist")
    void shouldReturnMaxDistance(final int[][] grid, final int expected) {
        assertThat(farLand.maxDistance(grid), is(expected));
    }
}