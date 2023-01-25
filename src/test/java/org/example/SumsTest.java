package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class SumsTest {

    private final Sums sums = new Sums();

    private static Stream<Arguments> provideNumsAndSums() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[]{-1, 0, 1, 2, -1, -4}, List.of(List.of(-1, -1, 2), List.of(-1, 0, 1))),
                Arguments.of(new int[]{0, 1, 1}, List.of()),
                Arguments.of(new int[]{0, 0, 0}, List.of(List.of(0, 0, 0))));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideNumsAndSums")
    void threeSum(final int[] nums, final List<List<Integer>> expected) {
        assertThat(sums.threeSum(nums), is(expected));
    }

}