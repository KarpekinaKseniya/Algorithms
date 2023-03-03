package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class JumpGameTest {

    private final JumpGame game = new JumpGame();

    private static Stream<Arguments> provideNumsAndMinNumberOfJump() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[]{2, 3, 1, 1, 4}, 2),
                Arguments.of(new int[]{2, 3, 0, 1, 4}, 2),
                Arguments.of(new int[]{3, 5, 1, 0, 4, 2, 1, 3}, 3));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideNumsAndMinNumberOfJump")
    void shouldJump(final int[] nums, final int expected) {
        assertThat(game.jump(nums), is(expected));
    }
}