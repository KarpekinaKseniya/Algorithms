package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WorkWithArraysTest {

    private final WorkWithArrays workWithArrays = new WorkWithArrays();

    @ParameterizedTest
    @MethodSource("provideArrayAndExpectedResult")
    void shouldFindMajorityElement(final int[] nums, final int expected) {
        assertThat(workWithArrays.findMajorityElement(nums), is(expected));
    }

    @Test
    void shouldThrowExceptionWhenCannotFindMajorityElement() {
        final int[] wrongNumsCount = new int[]{5, 5, 5, 5, 1, 1, 1, 1};
        final RuntimeException exception =
                assertThrows(RuntimeException.class, () -> workWithArrays.findMajorityElement(wrongNumsCount));
        assertThat(exception.getMessage(), is("Majority element not found"));
    }

    @ParameterizedTest
    @MethodSource("provideXandY")
    void shouldShuffleArray(final int[] nums, final int n, final int[] expected) {
        assertThat(workWithArrays.shuffle(nums, n), is(expected));
    }

    private static Stream<Arguments> provideXandY() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[]{2, 5, 1, 3, 4, 7}, 3, new int[]{2, 3, 5, 4, 1, 7}),
                Arguments.of(new int[]{1, 1, 2, 2}, 2, new int[]{1, 2, 1, 2}),
                Arguments.of(new int[]{1, 2, 3, 4, 4, 3, 2, 1}, 4, new int[]{1, 4, 2, 3, 3, 2, 4, 1}));
        //@formatter:on
    }

    private static Stream<Arguments> provideArrayAndExpectedResult() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[] { 3, 2, 3 }, 3),
                Arguments.of(new int[] { 2, 2, 1, 1, 1, 2, 2 }, 2),
                Arguments.of(new int[] { 5, 5, 5, 3, 2, 3, 5, 5, 1, 5, 3, 5, 5 }, 5));
        //@formatter:on
    }
}