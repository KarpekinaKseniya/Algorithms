package org.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ArmstrongNumberTest {

    private final ArmstrongNumber armstrongNumber = new ArmstrongNumber();

    @ParameterizedTest
    @CsvSource(value = {"1634:true", "123:false", "407:true"}, delimiter = ':')
    void shouldCheckIsArmstrongNumber(final int number, final boolean isArmstrong) {
        assertThat(armstrongNumber.isArmstrongNumber(number), is(isArmstrong));
    }
}