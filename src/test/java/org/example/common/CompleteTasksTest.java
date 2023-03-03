package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CompleteTasksTest {

    private final CompleteTasks completeTasks = new CompleteTasks();

    @ParameterizedTest
    @MethodSource("provideTasksAndExpectedMinRounds")
    void shouldReturnMinRounds(final int[] tasks, final int rounds) {
        assertThat(completeTasks.minimumRounds(tasks), is(rounds));
    }

    private static Stream<Arguments> provideTasksAndExpectedMinRounds() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[]{2,2,3,3,2,4,4,4,4,4}, 4),
                Arguments.of(new int[]{2,3,3}, -1),
                Arguments.of(new int[]{2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4}, 5)
        );
        //@formatter:on
    }
}