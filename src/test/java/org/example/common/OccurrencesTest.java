package org.example.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OccurrencesTest {

    private final Occurrences occurrences = new Occurrences();

    private static Stream<Arguments> provideArrayWithUniqueOccurrences() {
        //@formatter:off
        return Stream.of(
                Arguments.of((Object) new int[]{1,2,2,1,1,3}),
                Arguments.of((Object) new int[]{3}),
                Arguments.of((Object) new int[]{-3,0,1,-3,1,1,1,-3,10,0})
        );
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideArrayWithUniqueOccurrences")
    void shouldBeUniqueOccurrences(final int[] arr) {
        assertTrue(occurrences.uniqueOccurrences(arr));
    }

    @Test
    void shouldBeNotUniqueOccurrences() {
        assertFalse(occurrences.uniqueOccurrences(new int[]{1, 1, 2, 3, 3}));
    }
}