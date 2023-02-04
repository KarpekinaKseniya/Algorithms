package org.example;

import java.util.HashMap;
import java.util.Map;

/*
    Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
    In other words, return true if one of s1's permutations is the substring of s2.
*/
public class PermutationString {

    public boolean checkInclusion(final String s1, final String s2) {
        final Map<Character, Integer> map = new HashMap<>();
        final Map<Character, Integer> newMap = new HashMap<>();
        for (final char sym : s1.toCharArray()) {
            map.put(sym, map.getOrDefault(sym, 0) + 1);
        }
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            final String subString = s2.substring(i, i + s1.length());
            for (final char sym : subString.toCharArray()) {
                newMap.put(sym, newMap.getOrDefault(sym, 0) + 1);
            }
            if (map.equals(newMap)) {
                return true;
            } else newMap.clear();
        }
        return false;
    }
}
