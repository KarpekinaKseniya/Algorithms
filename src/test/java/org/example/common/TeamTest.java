package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class TeamTest {

    private final Team team = new Team();

    private static Stream<Arguments> provideScoresAndAges() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 5}, new int[]{8, 9, 10, 1}, 6),
                Arguments.of(new int[]{4, 5, 6, 5}, new int[]{2, 1, 2, 1}, 16),
                Arguments.of(new int[]{1, 3, 5, 10, 15}, new int[]{1, 2, 3, 4, 5}, 34));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideScoresAndAges")
    void shouldReturnBestTeamScore(final int[] scores, final int[] ages, final int expected) {
        assertThat(team.bestTeamScore(scores, ages), is(expected));
    }
}