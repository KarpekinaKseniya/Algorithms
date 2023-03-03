package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class PivotNumberTest {

    private final PivotNumber pivotNumber = new PivotNumber();

    @ParameterizedTest
    @CsvSource(value = {"8:6", "1:1", "4:-1", "1000:-1"}, delimiter = ':')
    void shouldReturnPivotNumber(final int number, final int expected) {
        assertThat(pivotNumber.pivotInteger(number), is(expected));
    }
}