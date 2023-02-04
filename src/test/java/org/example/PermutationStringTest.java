package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class PermutationStringTest {

    private final PermutationString permutationString = new PermutationString();

    @ParameterizedTest
    @CsvSource(value = {
            "ab:eidbaooo:true",
            "ab:eidboaoo:false",
            "bd:adbade:true"
    }, delimiter = ':')
    void shouldCheckInclusion(final String s1, final String s2, final boolean expected) {
        assertThat(permutationString.checkInclusion(s1, s2), is(expected));
    }
}