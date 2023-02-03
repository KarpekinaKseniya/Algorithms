package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class IsomorphicStringsTest {

    private final IsomorphicStrings isomorphic = new IsomorphicStrings();

    @ParameterizedTest
    //@formatter:off
    @CsvSource(value = {
            "egg:add:true",
            "bar:foo:false",
            "bear:bag:false",
            "title:paper:true"}, delimiter = ':')
    //@formatter:on
    void shouldCheckIsIsomorphic(final String s, final String t, final boolean expected) {
        assertThat(isomorphic.isIsomorphic(s, t), is(expected));
    }
}