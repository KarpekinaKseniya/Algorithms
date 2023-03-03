package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class BathTest {

    private final Bath bath = new Bath();

    private static Stream<Arguments> provideContainerHeights() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49),
                Arguments.of(new int[]{1, 4, 5, 1, 2, 4}, 16),
                Arguments.of(new int[]{1, 1}, 1));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideContainerHeights")
    void shouldReturnMaxArea(final int[] height, final int expected) {
        assertThat(bath.maxArea(height), is(expected));
    }
}