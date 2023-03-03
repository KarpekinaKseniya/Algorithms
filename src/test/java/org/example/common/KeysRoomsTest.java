package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class KeysRoomsTest {

    private final KeysRooms keysRooms = new KeysRooms();

    @ParameterizedTest
    @MethodSource("provideRoomsAndExpected")
    void shouldReturnCanVisitAllRooms(final List<List<Integer>> rooms, final boolean expected) {
        assertThat(keysRooms.canVisitAllRooms(rooms), is(expected));
    }

    private static Stream<Arguments> provideRoomsAndExpected() {
        //@formatter:off
        return Stream.of(
                Arguments.of(List.of(List.of(1), List.of(2), List.of(3), emptyList()), true),
                Arguments.of(List.of(List.of(1,3),List.of(3,0,1),List.of(2), List.of(0)), false),
                Arguments.of(List.of(List.of(2),emptyList(),List.of(1)), true)
        );
        //@formatter:on
    }
}