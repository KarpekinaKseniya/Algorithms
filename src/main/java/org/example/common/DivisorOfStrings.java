package org.example.common;

/*
    For two strings s and t, we say "t divides s" if and only
    if s = t + ... + t (i.e., t is concatenated with itself one or more times).
    Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
*/
public class DivisorOfStrings {

    private static final String EMPTY_STRING = "";

    public String gcdOfStrings(final String str1, final String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return EMPTY_STRING;
        }
        int index = findSubstringIndex(str1.length(), str2.length());
        return str2.substring(0, index);
    }

    private int findSubstringIndex(final int a, final int b) {
        return (b == 0) ? a : findSubstringIndex(b, a % b);
    }
}
