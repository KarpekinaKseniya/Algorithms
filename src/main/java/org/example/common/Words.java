package org.example.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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

    /*
        There is a strange printer with the following two special properties:
            The printer can only print a sequence of the same character each time.
            At each turn, the printer can print new characters starting from and ending
                at any place and will cover the original existing characters.
        Given a string s, return the minimum number of turns the printer needed to print it.
    */
    public int strangePrinter(final String s) {
        final int n = s.length();
        if (n == 0) return 0;

        int[][] dp = new int[101][101];
        for (int i = 0; i < n; i++) dp[i][i] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                dp[j][j + i] = i + 1;
                for (int k = j + 1; k <= j + i; k++) {
                    int temp = dp[j][k - 1] + dp[k][j + i];
                    if (s.charAt(k - 1) == s.charAt(j + i)) temp--;
                    dp[j][j + i] = Math.min(dp[j][j + i], temp);
                }
            }
        }
        return dp[0][n - 1];
    }

    //Given two strings s1 and s2, return the lowest ASCII sum of deleted characters
    // to make two strings equal.
    public int minimumDeleteSum(String string1, String string2) {
        if (string1.length() < string2.length()) return minimumDeleteSum(string2, string1);
        char[] s1 = string1.toCharArray(), s2 = string2.toCharArray();
        int[] memo = new int[s2.length];
        memo[memo.length - 1] = s2[s2.length - 1];
        for (int i = memo.length - 2; i > -1; i--) memo[i] += memo[i + 1] + s2[i];
        int[] ps = new int[s1.length];
        ps[ps.length - 1] = s1[s1.length - 1];
        for (int i = s1.length - 2; i > -1; i--) ps[i] += ps[i + 1] + s1[i];
        for (int i = s1.length - 1; i > -1; i--) {
            int last = ps[i], prevLast = i < s1.length - 1 ? ps[i + 1] : 0;
            for (int j = s2.length - 1; j > -1; j--) {
                int tmp = memo[j];
                if (s1[i] == s2[j]) last = memo[j] = prevLast;
                else last = memo[j] = Math.min(memo[j] + s1[i], last + s2[j]);
                prevLast = tmp;
            }
        }
        return memo[0];
    }

    /*
        Given a string s and a dictionary of strings wordDict, return true if s can be segmented
        into a space-separated sequence of one or more dictionary words.
        Note that the same word in the dictionary may be reused multiple times in the segmentation.
    */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /*
        Given a string s, check if it can be constructed by taking a substring of it and
        appending multiple copies of the substring together.
    */
    public boolean repeatedSubstringPattern(String s) {
        int l = s.length();
        for (int i = l / 2; i >= 1; i--) {
            if (l % i == 0) {
                int m = l / i;
                String subS = s.substring(0, i);
                StringBuilder sb = new StringBuilder();
                sb.append(subS.repeat(m));
                if (sb.toString().equals(s)) return true;
            }
        }
        return false;
    }

    /*
        Given a string s, rearrange the characters of s so that any two adjacent characters
        are not the same.
        Return any possible rearrangement of s or return "" if not possible.
    */
    public String reorganizeString(String s) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            hm.put(s.charAt(i), hm.getOrDefault(s.charAt(i), 0) + 1);
        }
        char maxFreqChar = '-';
        int maxFreq = 0;
        for (char c : hm.keySet()) {
            if (hm.get(c) > maxFreq) {
                maxFreq = hm.get(c);
                maxFreqChar = c;
            }
        }
        if (maxFreq > (s.length() + 1) / 2) {
            return "";
        }
        char[] result = new char[s.length()];
        int index = 0;
        for (int i = 0; i < maxFreq; i++) {
            result[index] = maxFreqChar;
            index += 2;
        }
        hm.remove(maxFreqChar);
        for (char c : hm.keySet()) {
            if (index >= s.length()) {
                index = 1;
            }
            for (int i = 0; i < hm.get(c); i++) {
                if (index >= s.length()) {
                    index = 1;
                }
                result[index] = c;
                index += 2;
            }
        }
        return new String(result);
    }

    /*
        Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
        An interleaving of two strings s and t is a configuration where s and t are divided into n and m
        substrings
        respectively, such that:
            s = s1 + s2 + ... + sn
            t = t1 + t2 + ... + tm
            |n - m| <= 1
        The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
        Note: a + b is the concatenation of strings a and b.
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = true;

                else if (i == 0)
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);

                else if (j == 0)
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);

                else
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /*
        You are given an array of words where each word consists of lowercase English letters.
        wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere
        in wordA without changing the order of the other characters to make it equal to wordB.
        For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
        A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1
        is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word
        is trivially a word chain with k == 1.
        Return the length of the longest possible word chain with words chosen from the given
        list of words.
    */
    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (String word : words) {
            int longestSubstring = 0;
            for (int i = 0; i < word.length(); i++) {
                String subWord = word.substring(0, i) + word.substring(i + 1);
                longestSubstring = Math.max(longestSubstring, map.getOrDefault(subWord, 0) + 1);
            }
            map.put(word, longestSubstring);
            max = Math.max(max, longestSubstring);
        }
        return max;
    }

    /*
        You are given two strings s and t.
        String t is generated by random shuffling string s and then add
        one more letter at a random position.
        Return the letter that was added to t.
    */
    public char findTheDifference(String s, String t) {
        int sum1 = 0;
        int sum2 = 0;
        for (char ch : s.toCharArray()) {
            sum1 = sum1 + ch;
        }
        for (char ch : t.toCharArray()) {
            sum2 = sum2 + ch;
        }
        return (char) (sum2 - sum1);
    }

    /*
        Given a string s, remove duplicate letters so that every letter appears once and only once.
        You must make sure your result is the smallest in lexicographical order among all possible
        results.
     */
    public String removeDuplicateLetters(String s) {
        HashMap<Character, Integer> inStack = new HashMap<>();
        HashMap<Character, Integer> freq = new HashMap<>();
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (inStack.containsKey(ch)) {
                freq.put(ch, freq.getOrDefault(ch, 0) - 1);
                continue;
            }
            while (stk.size() > 0 && stk.peek() > ch && freq.get(stk.peek()) >= 1) {
                inStack.remove(stk.peek());
                stk.pop();
            }
            stk.push(ch);
            inStack.put(ch, 1);
            freq.put(ch, freq.getOrDefault(ch, 0) - 1);
        }
        StringBuilder res = new StringBuilder();
        while (stk.size() > 0) {
            res.insert(0, stk.pop());
        }
        return res.toString();
    }
}
