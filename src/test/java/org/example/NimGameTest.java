package org.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NimGameTest {

    private final NimGame nimGame = new NimGame();

    @ParameterizedTest
    @CsvSource(value = { "34341:true", "8:false", "1534:true", "128:false" }, delimiter = ':')
    void shouldReturnCanWinNim(final int stones, final boolean expected) {
        assertThat(nimGame.canWinNim(stones), is(expected));
    }

    @Test
    void shouldThrownExceptionWhenWrongNumberOfStones() {
        final RuntimeException exception = assertThrows(IllegalArgumentException.class, () -> nimGame.canWinNim(-1));
        assertThat(exception.getMessage(), is("Wrong number of stones, n = -1"));
    }
}