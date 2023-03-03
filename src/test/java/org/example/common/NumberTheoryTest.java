package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class NumberTheoryTest {

    private final NumberTheory numberTheory = new NumberTheory();

    private static Stream<Arguments> provideABNumsAndExpected() {
        //@formatter:off
        return Stream.of(
                Arguments.of(12, 6, 4),
                Arguments.of(13, 5, 1),
                Arguments.of(25, 30, 2),
                Arguments.of(354, 1000, 2));
        //@formatter:on
    }

    private static Stream<Arguments> provideNumsArrayAndExpectedGCD() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[] { 2, 5, 6, 9, 10 }, 2),
                Arguments.of(new int[] { 7, 5, 6, 8, 3 }, 1),
                Arguments.of(new int[] { 3, 3 }, 3));
        //@formatter:on
    }

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

    @ParameterizedTest
    @CsvSource(value = { "3:2", "15:3136", "37:2082876103" }, delimiter = ':')
    void shouldReturnTribonacci(final int number, final int tribonacci) {
        assertThat(numberTheory.tribonacci(number), is(tribonacci));
    }

    @ParameterizedTest
    @CsvSource(value = { "278382788:569302584:145459898", "21:22:1", "8:10:1", "3:7:3" }, delimiter = ':')
    void shouldReturnCountOdds(final int low, final int high, final int count) {
        assertThat(numberTheory.countOdds(low, high), is(count));
    }
}