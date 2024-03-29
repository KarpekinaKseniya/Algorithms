package org.example.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PermutationString {

    /*
    Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
    In other words, return true if one of s1's permutations is the substring of s2.
    */
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
            } else
                newMap.clear();
        }
        return false;
    }

    // Given a string s, find the first non-repeating character in it and return its index.
    // If it does not exist, return -1.
    public int firstUniqChar(final String s) {
        final Map<Character, Integer> map = new LinkedHashMap<>();
        for (final char sym : s.toCharArray()) {
            map.put(sym, map.getOrDefault(sym, 0) + 1);
        }
        for (final Map.Entry<Character, Integer> entry : map.entrySet()) {
            final Character key = entry.getKey();
            final Integer count = entry.getValue();
            if (count == 1) {
                return s.indexOf(key);
            }
        }
        return -1;
    }

    /*
        Given an array of characters chars, compress it using the following algorithm:
        Begin with an empty string s. For each group of consecutive repeating characters in chars:
        If the group's length is 1, append the character to s.
        Otherwise, append the character followed by the group's length.
        The compressed string s should not be returned separately, but instead,
        be stored in the input character array chars. Note that group lengths that are 10 or longer
        will be split into multiple characters in chars.
        After you are done modifying the input array, return the new length of the array.
        You must write an algorithm that uses only constant extra space.
     */
    public int compress(final char[] chars) {
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            int count = 1;
            while (i < chars.length - 1 && chars[i] == chars[i + 1]) {
                count++;
                i++;
            }
            chars[index] = chars[i];
            index++;
            if (count > 1) {
                for (char ch : Integer.toString(count).toCharArray()) {
                    chars[index] = ch;
                    index++;
                }

            }
        }
        return index;
    }
}
