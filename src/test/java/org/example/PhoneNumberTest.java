package org.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PhoneNumberTest {

    private final PhoneNumber phoneNumber = new PhoneNumber();

    @ParameterizedTest
    @MethodSource("provideNumberAndResult")
    void shouldReformatNumber(final String number, final String expected) {
        assertThat(phoneNumber.reformatNumber(number), is(expected));
    }

    private static Stream<Arguments> provideNumberAndResult() {
        //@formatter:off
        return Stream.of(
                Arguments.of("123", "123"),
                Arguments.of("12  -56", "12-56"),
                Arguments.of("1-23-45 6", "123-456"),
                Arguments.of("123 4-567", "123-45-67"),
                Arguments.of("123 4-5678", "123-456-78")
        );
        //@formatter:on
    }
}