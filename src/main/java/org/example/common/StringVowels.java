package org.example.common;

import java.util.ArrayList;
import java.util.List;

/*
    Given an integer n, return the number of strings of length n that consist only
    of vowels (a, e, i, o, u) and are lexicographically sorted.

    A string s is lexicographically sorted if for all valid i, s[i] is the same as or
    comes before s[i+1] in the alphabet.
 */
public class StringVowels {

  public int countVowelStrings(final int n) {
    final char[] arr = new char[]{'a', 'e', 'i', 'o', 'u'};
    List<String> list = new ArrayList<>();
    backtrack(arr, n, list, 0, "");
    return list.size();
  }

  private void backtrack(final char[] arr, final int n, final List<String> list, final int j,
      final String word) {
    if (word.length() == n) {
      list.add(word);
    } else {
      for (int i = j; i < arr.length; i++) {
        backtrack(arr, n, list, i, word + arr[i]);
      }
    }
  }
}
