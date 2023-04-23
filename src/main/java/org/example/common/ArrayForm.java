package org.example.common;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ArrayForm {

    /*
        The array-form of an integer num is an array representing its digits in left to right order.
        For example, for num = 1321, the array form is [1,3,2,1].
        Given num, the array-form of an integer, and an integer k,
        return the array-form of the integer num + k.
    */
    public List<Integer> addToArrayForm(final int[] num, final int k) {
        final List<Integer> list = new LinkedList<>();
        int added = k;
        int carry = 0;
        for (int i = num.length - 1; i >= 0; i--) {
            int num1 = 0;
            if (added != 0) {
                num1 = added % 10;
                added /= 10;
            }
            final int num2 = num[i];
            final int sum = carry + num1 + num2;
            carry = sum / 10;
            list.add(sum % 10);
        }
        while (added != 0) {
            final int sum = carry + added % 10;
            added /= 10;
            carry = sum / 10;
            list.add(sum % 10);
        }
        if (carry != 0) {
            list.add(carry);
        }
        Collections.reverse(list);
        return list;
    }

    /*
        A program was supposed to print an array of integers.
        The program forgot to print whitespaces and the array is printed as a string of digits s
        and all we know is that all integers in the array were in the range [1, k] and there are
        no leading zeros in the array.

        Given the string s and the integer k, return the number of the possible arrays that
        can be printed as s using the mentioned program. Since the answer may be very large,
        return it modulo 109 + 7.
     */
    public int numberOfArrays(final String s, final int k) {
        final int mod = 1000_000_007;
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            long num = 0, sum = 0;
            for (int j = i; j < s.length(); j++) {
                num = num * 10 + (s.charAt(j) - '0');
                if (num == 0 || num > k)
                    break;
                sum += dp[j + 1];
            }
            dp[i] = (int) (sum % mod);
        }
        return dp[0];
    }
}
