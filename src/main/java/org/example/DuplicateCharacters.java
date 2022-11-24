package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/*
    Find duplicate characters in a String
*/
public class DuplicateCharacters {

    private static final int ONE_VALUE = 1;

    public char[] findDuplicateChar(final String word) {
        final Map<Character, Integer> map = new HashMap<>();
        for (final char value : word.toCharArray()) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + ONE_VALUE);
            } else {
                map.put(value, ONE_VALUE);
            }
        }

        //@formatter:off
        final char[] result = map.entrySet().stream()
                .filter(val -> val.getValue() > ONE_VALUE)
                .map(Map.Entry::getKey)
                .map(Objects::toString)
                .collect(Collectors.joining()).toCharArray();
        //@formatter:on

        return result.length == 0 ? null : result;
    }
}
