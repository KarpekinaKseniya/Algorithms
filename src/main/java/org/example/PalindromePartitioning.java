package org.example;

import java.util.ArrayList;
import java.util.List;

/*
    Given a string s, partition s such that every substring of the partition is a palindrome.
    Return all possible palindrome partitioning of s.
*/
public class PalindromePartitioning {

    public List<List<String>> partition(final String s) {
        final List<List<String>> res = new ArrayList<>();
        final List<String> cur = new ArrayList<>();
        partition(0, s, cur, res);
        return res;
    }

    private void partition(final int index, final String s, final List<String> cur, final List<List<String>> res) {
        if (index == s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {
                cur.add(s.substring(index, i + 1));
                partition(i + 1, s, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean isPalindrome(final String s, final int startWord, final int endWord) {
        int start = startWord;
        int end = endWord;
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
