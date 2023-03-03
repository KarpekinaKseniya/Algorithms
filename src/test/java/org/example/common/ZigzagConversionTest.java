package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ZigzagConversionTest {

    private final ZigzagConversion conversion = new ZigzagConversion();

    @ParameterizedTest
    //@formatter:off
    @CsvSource(value = {
            "PAYPALISHIRING:3:PAHNAPLSIIGYIR",
            "PAYPALISHIRING:4:PINALSIGYAHRPI",
            "F:1:F"}, delimiter = ':')
    //@formatter:on
    void shouldConvert(final String str, final int numRows, final String expected) {
        assertThat(conversion.convert(str, numRows), is(expected));
    }
}