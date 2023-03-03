package org.example.common;

import java.util.HashMap;
import java.util.Map;

/*
    Given two strings s and t, determine if they are isomorphic.
    Two strings s and t are isomorphic if the characters in s can be replaced to get t.
    All occurrences of a character must be replaced with another character while
    preserving the order of characters. No two characters may map to the same character,
    but a character may map to itself.
*/
public class IsomorphicStrings {
    public boolean isIsomorphic(final String s, final String t) {
        if (s.length() != t.length()) {
            return false;
        }
        final Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsValue(t.charAt(i))) {
                map.put(s.charAt(i), t.charAt(i));
            }

        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(map.get(s.charAt(i)));
        }
        return (sb.toString().equals(t));
    }
}
