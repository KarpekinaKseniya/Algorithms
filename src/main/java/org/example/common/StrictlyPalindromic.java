package org.example.common;

/*
    An integer n is strictly palindromic if, for every base b between 2 and n - 2 (inclusive),
    the string representation of the integer n in base b is palindromic.

    Given an integer n, return true if n is strictly palindromic and false otherwise.

    A string is palindromic if it reads the same forward and backward.
*/
public class StrictlyPalindromic {
    public boolean isStrictlyPalindromic(final int n) {
        for (int i = 2; i <= n - 2; i++) {
            if (!isPalindromic(Integer.toString(n, i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isPalindromic(final String number) {
        final char[] binary = number.toCharArray();
        final int ceilHalfOfLen = binary.length / 2 + 1;
        for (int i = 0; i < ceilHalfOfLen; i++) {
            if (binary[i] != binary[ceilHalfOfLen - i - 1]) {
                return false;
            }
        }
        return true;
    }
}
