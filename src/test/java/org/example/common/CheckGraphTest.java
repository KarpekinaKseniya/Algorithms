package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CheckGraphTest {

    private final CheckGraph graph = new CheckGraph();

    @ParameterizedTest
    @MethodSource("provideTestData")
    void shouldValidPath(final int n, final int[][] edges, final int source, final int destination,
                         final boolean expected) {
        assertThat(graph.validPath(n, edges, source, destination), is(expected));
    }

    private static Stream<Arguments> provideTestData() {
        //@formatter:off
        return Stream.of(
                Arguments.of(3, new int[][]{{0,1},{1,2},{2,0}}, 0, 2, true),
                Arguments.of(6, new int[][]{{0,1},{0,2},{3,5},{5,4},{4,3}}, 0, 5, false),
                Arguments.of(5, new int[][] {{0, 4}}, 0 ,4, true),
                Arguments.of(1, new int[][]{}, 0, 0, true),
                Arguments.of(10, new int[][]{{4,3},{1,4},{4,8},{1,7},{6,4},{4,2},{7,4},{4,0},{0,9},{5,4}}, 5, 9, true));
        //@formatter:on
    }
}