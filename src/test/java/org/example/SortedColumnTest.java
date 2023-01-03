package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class SortedColumnTest {

    private final SortedColumn sortedColumn = new SortedColumn();

    @ParameterizedTest
    @MethodSource("provideStringsAndExpectedCount")
    void minDeletionSize(final String[] strs, final int count) {
        assertThat(sortedColumn.minDeletionSize(strs), is(count));
    }

    private static Stream<Arguments> provideStringsAndExpectedCount() {
        return Stream.of(
                Arguments.of(new String[]{"cba", "daf", "ghi"}, 1),
                Arguments.of(new String[]{"a", "b"}, 0),
                Arguments.of(new String[]{"zyx", "wvu", "tsr"}, 3)
        );
    }
}