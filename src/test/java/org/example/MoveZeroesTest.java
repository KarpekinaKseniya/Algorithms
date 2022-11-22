package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MoveZeroesTest {

    private final MoveZeroes moveZeroes = new MoveZeroes();

    @ParameterizedTest
    @MethodSource("provideArrays")
    void shouldMoveZeroes(final int[] arr, final int[] expected) {
        moveZeroes.moveZeroes(arr);
        assertThat(arr, is(expected));
    }

    private static Stream<Arguments> provideArrays() {
        return Stream.of(
                Arguments.of(new int[]{0, 2, 3, 0, 9}, new int[]{2, 3, 9, 0, 0,}),
                Arguments.of(new int[]{0}, new int[]{0}),
                Arguments.of(new int[]{}, new int[]{}),
                Arguments.of(new int[]{1, 0, 3, 0, 0, 3, 2, 0}, new int[]{1, 3, 3, 2, 0, 0, 0, 0})
        );
    }
}