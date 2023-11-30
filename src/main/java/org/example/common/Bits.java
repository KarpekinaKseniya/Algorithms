package org.example.common;

public class Bits {

    /*
        Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
            ans[i] is the number of 1's in the binary representation of i.
    */
    public int[] countBits(final int n) {
        final int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = countOnes(i);
        }
        return result;
    }

    /*
        Given an integer n, you must transform it into 0 using the following operations any number
        of times:
            Change the rightmost (0th) bit in the binary representation of n.
            Change the ith bit in the binary representation of n if the (i-1)th bit is set to 1
            and the (i-2)th through 0th bits are set to 0.
        Return the minimum number of operations to transform n into 0.
    */
    public int minimumOneBitOperations(int n) {
        int res = 0;
        for (int temp = n; temp != 0; temp = temp / 2) {
            res ^= temp;
        }
        return res;
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
