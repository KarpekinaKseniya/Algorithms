package org.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PascalTriangleTest {

    private final PascalTriangle triangle = new PascalTriangle();

    private static Stream<Arguments> provideRowIndexAndRow() {
        //@formatter:off
        return Stream.of(
                Arguments.of(0, List.of(1)),
                Arguments.of(3, List.of(1, 3, 3, 1)),
                Arguments.of(6, List.of(1, 6, 15, 20, 15, 6, 1)),
                Arguments.of(11, List.of(1, 11, 55, 165, 330, 462, 462, 330, 165, 55, 11, 1)));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideRowIndexAndRow")
    void shouldReturnRow(final int rowIndex, final List<Integer> expected) {
        assertThat(triangle.getRow(rowIndex), is(expected));
    }
}