package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AsterisksTest {

    private final Asterisks asterisks = new Asterisks();

    @ParameterizedTest
    @CsvSource(value = {"l|*e*et|c**o|*de|:2", "iamprogrammer:0", "yo|uar|e**|b|e***au|tifu|l:5", "iamprogram***mer:3", "iamp*rog*rammer*****:7"}, delimiter = ':')
    void shouldCountAsterisks(final String s, final int count) {
        assertThat(asterisks.countAsterisks(s), is(count));
    }
}