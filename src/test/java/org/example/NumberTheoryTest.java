package org.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class NumberTheoryTest {

    private final NumberTheory numberTheory = new NumberTheory();

    @ParameterizedTest
    @MethodSource("provideNumsArrayAndExpectedGCD")
    void shouldFindGCD(final int[] nums, final int expected) {
        assertThat(numberTheory.findGCD(nums), is(expected));
    }

    @ParameterizedTest
    @CsvSource(value = { "8:8", "1:2", "121:242", "1000:1000" }, delimiter = ':')
    void shouldReturnSmallestEvenMultiple(final int num, final int expected) {
        assertThat(numberTheory.smallestEvenMultiple(num), is(expected));
    }

    @ParameterizedTest
    @MethodSource("provideABNumsAndExpected")
    void shouldReturnCommonFactors(final int a, final int b, final int expected) {
        assertThat(numberTheory.commonFactors(a, b), is(expected));
    }

    @ParameterizedTest
    @CsvSource(value = { "7:1", "121:2", "1248:4" }, delimiter = ':')
    void shouldCountDigits(final int number, final int count) {
        assertThat(numberTheory.countDigits(number), is(count));
    }

    private static Stream<Arguments> provideABNumsAndExpected() {
        //@formatter:off
        return Stream.of(
                Arguments.of(12, 6, 4),
                Arguments.of(13, 5, 1),
                Arguments.of(25, 30, 2),
                Arguments.of(354, 1000, 2)
        );
        //@formatter:on
    }

    private static Stream<Arguments> provideNumsArrayAndExpectedGCD() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[] { 2, 5, 6, 9, 10 }, 2),
                Arguments.of(new int[] { 7, 5, 6, 8, 3 }, 1),
                Arguments.of(new int[] { 3, 3 }, 3)
        );
        //@formatter:on
    }
}