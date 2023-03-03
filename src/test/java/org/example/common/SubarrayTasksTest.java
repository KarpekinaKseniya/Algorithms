package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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

    @ParameterizedTest
    @MethodSource("provideNUmsAndSubsequences")
    void shouldReturnSubsequences(final int[] nums, final List<List<Integer>> expected) {
        assertThat(circular.findSubsequences(nums), is(expected));
    }

    private static Stream<Arguments> provideNUmsAndSubsequences() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[] {4, 11, 7, 1}, List.of(List.of(4, 11), List.of(4, 7))),
                Arguments.of(new int[] {4, 4, 3, 2, 1}, List.of(List.of(4, 4)))
        );
        //@formatter:on
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