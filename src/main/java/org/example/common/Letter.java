package org.example.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Letter {

    /*
        You are given an array of characters letters that is sorted in non-decreasing order,
        and a character target. There are at least two different characters in letters.
        Return the smallest character in letters that is lexicographically greater than target.
        If such a character does not exist, return the first character in letters.
    */
    public char nextGreatestLetter(final char[] letters, final char target) {
        for (final char letter : letters)
            if (letter > target)
                return letter;
        return letters[0];
    }

    /*
        A string s is called good if there are no two different characters in s that have
        the same frequency.
        Given a string s, return the minimum number of characters you need to delete to make s good.
        The frequency of a character in a string is the number of times it appears in the string.
        For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
    */
    public int minDeletions(String s) {
        int count = 0;
        Map<Character, Integer> hm = new HashMap<>();
        char[] ch = s.toCharArray();
        for (final char value : ch) {
            hm.put(value, hm.getOrDefault(value, 0) + 1);
        }
        Set<Integer> set = new HashSet<>();
        for (char c : hm.keySet()) {
            int freq = hm.get(c);
            if (!set.contains(freq)) {
                set.add(freq);
            } else {
                while (freq > 0 && set.contains(freq)) {
                    freq--;
                    count++;
                }
                if (freq > 0) set.add(freq);
            }
        }
        return count;
    }
}
