package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class GroupsTest {

    private final Groups groups = new Groups();

    @ParameterizedTest
    @MethodSource("provideRequestAndExpectedValues")
    void shouldGroupThePeople(final int[] sizes, final List<List<Integer>> expected) {
        assertThat(groups.groupThePeople(sizes), is(expected));
    }

    private static Stream<Arguments> provideRequestAndExpectedValues() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[]{3, 3, 3, 3, 3, 1, 3}, List.of(List.of(0, 1, 2), List.of(5), List.of(3, 4, 6))),
                Arguments.of(new int[]{2, 1, 3, 3, 3, 2}, List.of(List.of(1), List.of(2, 3, 4), List.of(0, 5)))
        );
        //@formatter:on
    }
}