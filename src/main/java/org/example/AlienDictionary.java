package org.example;

import java.util.HashMap;
import java.util.Map;

/*
    In an alien language, surprisingly, they also use English lowercase letters,
    but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
    Given a sequence of words written in the alien language, and the order of the alphabet,
    return true if and only if the given words are sorted lexicographically in this alien language.
*/
public class AlienDictionary {

    public boolean isAlienSorted(final String[] words, final String order) {
        Map<Character, Integer> map = new HashMap<>();
        int index = 1;
        int maxSize = Integer.MIN_VALUE;
        for (final char c : order.toCharArray()) {
            map.put(c, index);
            index++;
        }
        for (final String word : words) {
            maxSize = Math.max(maxSize, word.length());
        }
        for (int i = 0; i < maxSize; i++) {
            int prev = 0;
            int pos = 0;
            int strictlyHigh = 0;
            for (String word : words) {
                if (word.length() > i) {
                    pos = map.get(word.charAt(i));
                } else {
                    pos = 0;
                }
                if (pos < prev) {
                    return false;
                } else if (pos > prev) {
                    strictlyHigh++;
                }
                prev = pos;
            }
            if (strictlyHigh == words.length) {
                return true;
            }
        }
        return true;
    }

}
