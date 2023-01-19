package org.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SubarrayTasksTest {

    private final SubarrayTasks circular = new SubarrayTasks();

    @ParameterizedTest
    @MethodSource("provideNumsAndMaxSum")
    void shouldReturnMaxSubarraySumCircular(final int[] nums, final int sum) {
        assertThat(circular.maxSubarraySumCircular(nums), is(sum));
    }

    @ParameterizedTest
    @MethodSource("provideNumsAndK")
    void shouldReturnCountOfSubarrayDivByK(final int[] nums, final int k, final int count) {
        assertThat(circular.subarrayDivByK(nums, k), is(count));
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

    private static Stream<Arguments> provideNumsAndK() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[] { 4, 5, 0, -2, -3, 1 }, 5, 7),
                Arguments.of(new int[] { 5 }, 9, 0),
                Arguments.of(new int[] {3, 23, 11, 7}, 7, 1)
        );
        //@formatter:on
    }
}