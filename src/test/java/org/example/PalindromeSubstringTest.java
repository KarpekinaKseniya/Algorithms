package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class PalindromeSubstringTest {

    private final PalindromeSubstring palindrome = new PalindromeSubstring();

    @ParameterizedTest
    @CsvSource(value = {"babad:bab", "cbbd:bb", "resdsfefvcvdsdf:sds", "asd:a"}, delimiter = ':')
    void shouldReturnLongestPalindrome(final String str, final String pal) {
        assertThat(palindrome.longestPalindrome(str), is(pal));
    }
}