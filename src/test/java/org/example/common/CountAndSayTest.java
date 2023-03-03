package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CountAndSayTest {

    private final CountAndSay say = new CountAndSay();

    @ParameterizedTest
    @CsvSource(value = { "1:1", "6:312211", "11:11131221133112132113212221", "7:13112221", "4:1211" }, delimiter = ':')
    void shouldReturnCountAndSayString(final int n, final String expected) {
        assertThat(say.countAndSay(n), is(expected));
    }
}