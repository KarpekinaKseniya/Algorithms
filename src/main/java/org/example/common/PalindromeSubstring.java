package org.example.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Given a string s, return the longest palindromic substring in s.
*/
public class PalindromeSubstring {

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
}
