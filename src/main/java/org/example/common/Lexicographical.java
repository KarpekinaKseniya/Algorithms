package org.example.common;

/*
    You are given two strings of the same length s1 and s2 and a string baseStr.
    We say s1[i] and s2[i] are equivalent characters.
    For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
    Equivalent characters follow the usual rules of any equivalence relation:
        Reflexivity: 'a' == 'a'.
        Symmetry: 'a' == 'b' implies 'b' == 'a'.
        Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
    For example, given the equivalency information from s1 = "abc" and s2 = "cde",
    "acd" and "aab" are equivalent strings of baseStr = "eed", and "aab" is the lexicographically
    smallest equivalent string of baseStr.
    Return the lexicographically smallest equivalent string of baseStr by using the equivalency
    information from s1 and s2.
*/
public class Lexicographical {

    public String smallestEquivalentString(final String s1, final String s2, final String baseStr) {
        char[] c = new char[26];
        for (int i = 0; i < 26; i++) {
            c[i] = (char) ('a' + i);
        }
        for (int i = 0; i < s1.length(); i++) {
            char min_char = 'a';
            char max_char = 'a';
            final char c1 = c[s1.charAt(i) - 'a'];
            final char c2 = c[s2.charAt(i) - 'a'];
            if (c1 > c2) {
                min_char = c2;
                max_char = c1;
            } else {
                max_char = c2;
                min_char = c1;
            }

            for (int j = 0; j < 26; j++) {
                if (c[j] == max_char) c[j] = min_char;
            }
        }
        StringBuilder res = new StringBuilder();
        for (char ch : baseStr.toCharArray()) {
            res.append(c[ch - 'a']);
        }
        return res.toString();
    }
}
