package org.example.url;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RestoreAddressTest {

    private final RestoreAddress address = new RestoreAddress();

    @ParameterizedTest
    @MethodSource("provideAddresses")
    void restoreIpAddresses(final String str, final List<String> expected) {
        assertThat(address.restoreIpAddresses(str), is(expected));
    }

    private static Stream<Arguments> provideAddresses() {
        //@formatter:off
        return Stream.of(
                Arguments.of("25525511135", List.of("255.255.11.135", "255.255.111.35")),
                Arguments.of("0000", List.of("0.0.0.0")),
                Arguments.of("101023", List.of("1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3"))
        );
        //@formatter:on
    }
}