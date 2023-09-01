package org.example.common;

/*
    Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
        ans[i] is the number of 1's in the binary representation of i.
*/
public class Bits {

    public int[] countBits(final int n) {
        final int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = countOnes(i);
        }
        return result;
    }

    private int countOnes(final int value) {
        final String binaryValue = Integer.toBinaryString(value);
        int count = 0;
        for (Character sym : binaryValue.toCharArray()) {
            if (sym.equals('1')) {
                count++;
            }
        }
        return count;
    }

}
