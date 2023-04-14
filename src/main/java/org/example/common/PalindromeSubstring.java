package org.example.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromeSubstring {

    /*
        Given a string s, return the longest palindromic substring in s.
    */
    public String longestPalindrome(final String s) {
        final Map<Character, List<Integer>> charMap = new HashMap<>();
        String longestPalindrome = "";
        for (int i = 0; i < s.length(); i++) {
            final char characterAtIndex = s.charAt(i);
            if (charMap.containsKey(characterAtIndex)) {
                final List<Integer> charIndexList = charMap.get(characterAtIndex);
                for (int j = 0; j < charIndexList.size() && (i - charIndexList.get(j) + 1) > longestPalindrome.length(); j++) {
                    final String subString = s.substring(charIndexList.get(j), i + 1);
                    final String reverse = new StringBuilder(subString).reverse().toString();
                    if (subString.equals(reverse)) {
                        longestPalindrome = subString;
                        charIndexList.add(i);
                        break;
                    }
                }
                charIndexList.add(i);
            } else {
                final List<Integer> charIndexList = new ArrayList<>();
                charIndexList.add(i);
                charMap.put(characterAtIndex, charIndexList);
            }
        }
        return longestPalindrome.isEmpty() ? s.charAt(0) + "" : longestPalindrome;
    }

    /*
        Given a string s, find the longest palindromic subsequence's length in s.

        A subsequence is a sequence that can be derived from another sequence by deleting some
        or no elements without changing the order of the remaining elements.
    */
    public int longestPalindromeSubseq(final String s) {
        final char[] c = s.toCharArray();
        int[] dp = new int[c.length];
        for (int j = 0; j < dp.length; j++) {
            dp[j] = 1;
            int topRight = 0;
            for (int i = j - 1; i >= 0; i--) {
                int temp = dp[i];
                dp[i] = c[i] == c[j] ? 2 + topRight : Math.max(dp[i], dp[i + 1]);
                topRight = temp;
            }
        }
        return dp[0];
    }
}
