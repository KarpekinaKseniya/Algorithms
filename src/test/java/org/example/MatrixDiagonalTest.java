package org.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MatrixDiagonalTest {

    private final MatrixDiagonal matrixDiagonal = new MatrixDiagonal();

    @ParameterizedTest
    @MethodSource("validMatrix")
    void shouldReturnDiagonalSum(final int[][] matrix, final int expected) {
        assertThat(matrixDiagonal.diagonalSum(matrix), is(expected));
    }

    @ParameterizedTest
    @MethodSource("notValidMatrix")
    void shouldThrownException(final int[][] matrix) {
        final RuntimeException actual =
                assertThrows(IllegalArgumentException.class, () -> matrixDiagonal.diagonalSum(matrix));
        assertThat(actual.getMessage(), is("Input matrix is not valid"));
    }

    private static Stream<Arguments> validMatrix() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }, 25),
                Arguments.of(new int[][] { { 5 } }, 5),
                Arguments.of(new int[][] { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 } }, 8)
        );
        //@formatter:on
    }

    private static Stream<Arguments> notValidMatrix() {
        //@formatter:off
        return Stream.of(
                Arguments.of((Object) new int[][] { { 3 }, { 4, 5, 6 }, { 7, 8, 9 } }),
                Arguments.of((Object) new int[][] { { 5 }, { 0 }}),
                Arguments.of((Object) new int[][] { { 1, 1, 1, 1 }, { 1 }, { 1, 1 }, {  } })
        );
        //@formatter:on
    }
}