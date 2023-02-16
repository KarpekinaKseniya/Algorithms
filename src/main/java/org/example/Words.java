package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Words {

    /*
        Given a pattern and a string s, find if s follows the same pattern.
        Here follow means a full match,
        such that there is a bijection between a letter in pattern and a non-empty word in s.
    */
    public boolean wordPattern(final String pattern, final String s) {
        final String[] sWords = s.split(" ");
        if (pattern.length() != sWords.length) {
            return false;
        }
        final Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            final char letter = pattern.charAt(i);
            if (!map.isEmpty()) {
                if (map.containsKey(letter) && !map.get(letter).equals(sWords[i])) {
                    return false;
                }
                if (map.containsKey(letter) && map.get(letter).equals(sWords[i])) {
                    continue;
                }
                if (map.containsValue(sWords[i])) {
                    return false;
                }
            }
            map.put(letter, sWords[i]);
        }
        return true;
    }

    /*
        We define the usage of capitals in a word to be right when one of the following cases holds:
            All letters in this word are capitals, like "USA".
            All letters in this word are not capitals, like "boring".
            Only the first letter in this word is capital, like "Google".
        Given a string word, return true if the usage of capitals in it is right.
     */
    public boolean detectCapitalUse(final String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }

        if (Character.isUpperCase(word.charAt(0))) {
            final boolean isFirstCharacter = Character.isUpperCase(word.charAt(1));
            for (int i = 2; i < word.length(); i++) {
                final boolean currentCharState = Character.isUpperCase(word.charAt(i));
                if (currentCharState != isFirstCharacter) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < word.length(); i++) {
                if (Character.isUpperCase(word.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
        You are given a string s and an array of strings words. All the strings of words are of the same length.
        A concatenated substring in s is a substring that contains all the strings of any permutation of words concatenated.
        For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab"
        are all concatenated strings. "acdbef" is not a concatenated substring because it is not the concatenation of any permutation of words.
        Return the starting indices of all the concatenated substrings in s. You can return the answer in any order.
    */
    public List<Integer> findSubstring(final String s, final String[] words) {
        final Map<String, Integer> map = new HashMap<>();
        final int wordLength = words[0].length();
        int len = 0;
        for (final String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
            len += word.length();
        }
        final List<Integer> indexes = new ArrayList<>();
        final int times = s.length() - len;
        for (int i = 0; i <= times; i++) {
            if (map.isEmpty()) {
                for (final String word : words) {
                    if (map.containsKey(word)) {
                        map.put(word, map.get(word) + 1);
                    } else {
                        map.put(word, 1);
                    }
                }
            }
            String sub = s.substring(i, i + len);
            int count = 0;
            while (count < words.length) {
                final String word = sub.substring(0, wordLength);
                final Integer freq = map.get(word);
                if (freq != null && freq > 0) {
                    map.put(word, freq - 1);
                    sub = sub.substring(wordLength);
                    count++;
                } else
                    break;
            }
            if (count == words.length) {
                indexes.add(i);
            }
            map.clear();
        }
        return indexes;
    }

    /*
        Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
        A concatenated word is defined as a string that is composed entirely of at least two shorter words in the given array.
    */
    public List<String> findAllConcatenatedWordsInADict(final String[] words) {
        final List<String> result = new ArrayList<>();
        final Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            set.remove(word);
            if (trim(set, word)) {
                result.add(word);
            }
            set.add(word);
        }
        return result;
    }

    private boolean trim(final Set<String> set, final String word) {
        if (set.contains(word)) {
            return true;
        }
        for (int i = 1; i < word.length(); i++) {
            if (set.contains(word.substring(0, i)) && trim(set, word.substring(i))) {
                return true;
            }
        }
        return false;
    }
}
