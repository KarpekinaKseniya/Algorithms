package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ArrayFormTest {

    private final ArrayForm form = new ArrayForm();

    private static Stream<Arguments> provideNumAndK() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[]{1, 2, 0, 0}, 34, List.of(1, 2, 3, 4)),
                Arguments.of(new int[]{2, 7, 4}, 181, List.of(4, 5, 5)),
                Arguments.of(new int[]{2, 1, 5}, 806, List.of(1, 0, 2, 1)),
                Arguments.of(new int[]{9, 9, 9, 9, 9}, 1, List.of(1, 0, 0, 0, 0, 0)));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideNumAndK")
    void shouldAddToArrayForm(final int[] num, final int k, final List<Integer> expected) {
        assertThat(form.addToArrayForm(num, k), is(expected));
    }
}