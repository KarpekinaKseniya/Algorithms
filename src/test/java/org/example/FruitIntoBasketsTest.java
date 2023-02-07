package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class FruitIntoBasketsTest {

    private final FruitIntoBaskets baskets = new FruitIntoBaskets();

    private static Stream<Arguments> provideFruitsInBasket() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[]{1, 2, 1}, 3),
                Arguments.of(new int[]{0, 1, 2, 2}, 3),
                Arguments.of(new int[]{1, 2, 3, 2, 2}, 4));
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideFruitsInBasket")
    void shouldReturnTotalFruit(final int[] fruits, final int expected) {
        assertThat(baskets.totalFruit(fruits), is(expected));
    }
}