package org.example.common;

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
        We can scramble a string s to get a string t using the following algorithm:
            If the length of the string is 1, stop.
        If the length of the string is > 1, do the following:
            Split the string into two non-empty substrings at a random index, i.e.,
                if the string is s, divide it to x and y where s = x + y.
            Randomly decide to swap the two substrings or to keep them in the same order. i.e.,
                after this step, s may become s = x + y or s = y + x.
            Apply step 1 recursively on each of the two substrings x and y.
        Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1,
            otherwise, return false.
     */
    public boolean isScramble(final String s1, final String s2) {
        final int n = s1.length();
        if (n != s2.length()) {
            return false;
        }
        boolean[][][] dp = new boolean[n + 1][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[1][i][j] = true;
                }
            }
        }
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                for (int j = 0; j <= n - l; j++) {
                    for (int k = 1; k < l; k++) {
                        if ((dp[k][i][j] && dp[l - k][i + k][j + k]) ||
                                (dp[k][i][j + l - k] && dp[l - k][i + k][j])) {
                            dp[l][i][j] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[n][0][0];
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
    Given two strings word1 and word2, return the minimum number of operations required
    to convert word1 to word2.
    You have the following three operations permitted on a word:
        Insert a character
        Delete a character
        Replace a character
    */
    public int minDistance(final String word1, final String word2) {
        final int m = word1.length();
        final int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        for (int j = 0; j <= n; j++)
            dp[0][j] = j;
        for (int i = 0; i <= m; i++)
            dp[i][0] = i;
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    int min = Math.min(dp[i][j - 1], dp[i - 1][j]);
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], min);
                }
            }
        return dp[m][n];
    }

    /*
        Given a string s, partition the string into one or more substrings such that
        the characters in each substring are unique. That is, no letter appears in a single
        substring more than once.
        Return the minimum number of substrings in such a partition.
        Note that each character should belong to exactly one substring in a partition.
    */
    public int partitionString(final String s) {
        int count = (s.isEmpty()) ? 0 : 1;
        final String lowerS = s.toLowerCase();
        final HashSet<Character> letter = new HashSet<>();
        for (int i = 0; i < lowerS.length(); i++) {
            if (letter.contains(lowerS.charAt(i))) {
                letter.clear();
                count++;
            }
            letter.add(lowerS.charAt(i));
        }
        return count;
    }

    /*
        You are given a string s, which contains stars *.
        In one operation, you can:
            Choose a star in s.
            Remove the closest non-star character to its left, as well as remove the star itself.
            Return the string after all stars have been removed.
        Note:
            The input will be generated such that the operation is always possible.
            It can be shown that the resulting string will always be unique.
     */
    public String removeStars(final String s) {
        String result = "";
        int cntStar = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '*') cntStar++;
            else if (cntStar == 0) result = s.charAt(i) + result;
            else cntStar--;
        }
        return result;
    }

    /*
        You are given a list of strings of the same length words and a string target.

        Your task is to form target using the given words under the following rules:
            target should be formed from left to right.
            To form the ith character (0-indexed) of target, you can choose the kth character of
            the jth string in words if target[i] = words[j][k].
            Once you use the kth character of the jth string of words, you can no longer use
            the xth character of any string in words where x <= k. In other words, all characters
            to the left of or at index k become unusuable for every string.
            Repeat the process until you form the string target.
            Notice that you can use multiple characters from the same string in words provided
            the conditions above are met.

        Return the number of ways to form target from words. Since the answer may be too large,
        return it modulo 109 + 7.
    */
    public int numWays(final String[] words, final String target) {
        final int mod = 1000000007;
        final int n = words[0].length();
        int[][] freq = new int[n][26];
        for (final String word : words) {
            for (int j = 0; j < n; j++) {
                freq[j][word.charAt(j) - 'a']++;
            }
        }
        int[][] dp = new int[n + 1][target.length() + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target.length(); j++) {
                final int charCount = freq[i - 1][target.charAt(j - 1) - 'a'];
                dp[i][j] = (dp[i - 1][j] + (int) ((long) charCount * dp[i - 1][j - 1] % mod)) % mod;
            }
        }

        return dp[n][target.length()];
    }

    /*
        You are given two strings word1 and word2.
        Merge the strings by adding letters in alternating order, starting with word1.
        If a string is longer than the other, append the additional letters onto the end of
        the merged string.

        Return the merged string.
     */
    public String mergeAlternately(final String word1, final String word2) {
        final int len1 = word1.length();
        final int len2 = word2.length();
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < len1 || j < len2) {
            if (i < word1.length())
                sb.append(word1.charAt(i++));
            if (j < word2.length())
                sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }

    /*
        Given two strings s and goal, return true if you can swap two letters in s
        so the result is equal to goal, otherwise, return false.
        Swapping letters is defined as taking two indices i and j (0-indexed) such
        that i != j and swapping the characters at s[i] and s[j].
        For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
    */
    public boolean buddyStrings(final String s, final String goal) {
        int[] a = new int[26], b = new int[26];
        char[] AC = s.toCharArray(), BC = goal.toCharArray();

        boolean swapAble = false, allPresent = false;
        int diff = 0;
        if (AC.length == BC.length) {
            for (int i = 0; i < AC.length; i++) {
                a[AC[i] - 'a']++;
                b[BC[i] - 'a']++;
                if (AC[i] != BC[i]) {
                    ++diff;
                }
            }
            allPresent = true;
            for (int i = 0; i < 26 && allPresent; i++) {
                if (a[i] != b[i]) {
                    allPresent = false;
                }
                if (2 <= a[i]) {
                    swapAble = true;
                }
            }
        }
        return (2 == diff || (diff == 0 && swapAble)) && allPresent;
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
