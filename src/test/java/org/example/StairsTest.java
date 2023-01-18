package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class StairsTest {

    private final Stairs stairs = new Stairs();

    @ParameterizedTest
    @CsvSource(value = {"2:2", "3:3", "45:1836311903", "1:1"}, delimiter = ':')
    void shouldReturnClimbStairs(final int n, final int expected) {
        assertThat(stairs.climbStairs(n), is(expected));
    }
}