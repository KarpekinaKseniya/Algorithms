package org.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CircularSubarrayTest {

    private final CircularSubarray circular = new CircularSubarray();

    @ParameterizedTest
    @MethodSource("provideNumsAndMaxSum")
    void shouldReturnMaxSubarraySumCircular(final int[] nums, final int sum) {
        assertThat(circular.maxSubarraySumCircular(nums), is(sum));
    }

    private static Stream<Arguments> provideNumsAndMaxSum() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[] { 1, -2, 3, -2 }, 3),
                Arguments.of(new int[] { 5, -3, 5 }, 10),
                Arguments.of(new int[] { -3, -2, -3 }, -2)
        );
        //@formatter:on
    }
}