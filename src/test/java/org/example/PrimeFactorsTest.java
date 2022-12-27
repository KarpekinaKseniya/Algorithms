package org.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrimeFactorsTest {

    private final PrimeFactors primeFactors = new PrimeFactors();

    @ParameterizedTest
    @MethodSource("provideNumberAndHisPrimeFactors")
    void findAllPrimeFactors(final int number, final int[] primes) {
        assertThat(primeFactors.findAllPrimeFactors(number), is(primes));
    }

    private static Stream<Arguments> provideNumberAndHisPrimeFactors() {
        //@formatter:off
        return Stream.of(
                Arguments.of(67768565, new int[]{5, 97, 139729}),
                Arguments.of(2, new int[]{2}),
                Arguments.of(3, new int[]{3}),
                Arguments.of(5, new int[]{5}),
                Arguments.of(121, new int[]{11, 11}),
                Arguments.of(5341, new int[]{7, 7, 109}),
                Arguments.of(981, new int[]{3, 3, 109}),
                Arguments.of(1230, new int[]{2, 3, 5, 41}),
                Arguments.of(5634, new int[]{2, 3, 3, 313}),
                Arguments.of(977, new int[]{977}));
        //@formatter:on
    }
}