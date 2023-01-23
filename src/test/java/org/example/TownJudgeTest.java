package org.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TownJudgeTest {

    private final TownJudge town = new TownJudge();

    @ParameterizedTest
    @MethodSource("provideNumberOfPeopleAndTrust")
    void findJudge(final int n, final int[][] trust, final int expected) {
        assertThat(town.findJudge(n, trust), is(expected));
    }

    private static Stream<Arguments> provideNumberOfPeopleAndTrust() {
        //@formatter:off
        return Stream.of(
                Arguments.of(1, new int[][]{}, 1),
                Arguments.of(4, new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}}, 3),
                Arguments.of(3, new int[][]{{1, 2}, {2, 3}}, -1),
                Arguments.of(3, new int[][]{{1, 3}, {2, 3}, {3, 1}}, -1),
                Arguments.of(3, new int[][]{{1, 3}, {2, 3}}, 3),
                Arguments.of(2, new int[][]{{1, 2}}, 2));
        //@formatter:on
    }
}