package org.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StrictlyPalindromicTest {

    private final StrictlyPalindromic strictlyPalindromic = new StrictlyPalindromic();

    @ParameterizedTest
    @CsvSource(value = { "9:false", "4:false", "22:false", "7:false", "1:true" }, delimiter = ':')
    void shouldCheckIsStrictlyPalindromic(final int number, final boolean isStrictly) {
        assertThat(strictlyPalindromic.isStrictlyPalindromic(number), is(isStrictly));
    }
}