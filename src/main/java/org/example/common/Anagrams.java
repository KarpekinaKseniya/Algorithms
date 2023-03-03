package org.example.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Given two strings s and p, return an array of all the start indices of p's anagrams in s.
    You may return the answer in any order.
    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
    typically using all the original letters exactly once.
*/
public class Anagrams {

    public List<Integer> findAnagrams(final String s, final String p) {
        final List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }
        int[] ct1 = new int[26];
        int[] ct2 = new int[26];
        int k = p.length();
        for (final char it : p.toCharArray()) {
            ct1[it - 'a']++;
        }
        int i = 0;
        while (i < k) {
            ct2[s.charAt(i++) - 'a']++;
        }
        k = s.length();
        int j = 0;
        if (Arrays.equals(ct1, ct2)) {
            result.add(j);
        }
        while (i < k) {
            ct2[s.charAt(j++) - 'a']--;
            ct2[s.charAt(i++) - 'a']++;
            if (Arrays.equals(ct1, ct2)) {
                result.add(j);
            }
        }
        return result;
    }
}
