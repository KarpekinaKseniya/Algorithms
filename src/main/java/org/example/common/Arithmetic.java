package org.example.common;

public class Arithmetic {

    /*
        Given an array nums of integers, return the length of the longest arithmetic
        subsequence in nums.
        Note that:
        A subsequence is an array that can be derived from another array by deleting some or
        no elements without changing the order of the remaining elements.
        A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same
        value (for 0 <= i < seq.length - 1).
    */
    public int longestArithSeqLength(final int[] nums) {
        int n = nums.length, max = 0, dp[][] = new int[n][1001];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                final int diff = nums[j] - nums[i] + 500;
                dp[i][diff] = dp[j][diff] + 1;
                max = Math.max(max, dp[i][diff]);
            }
        }
        return max + 1;
    }

    /*
        We build a table of n rows (1-indexed). We start by writing 0 in the 1st row.
        Now in every subsequent row, we look at the previous row and replace each occurrence
        of 0 with 01, and each occurrence of 1 with 10.
        For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
        Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table
        of n rows.
    */
    public int kthGrammar(int n, int k) {
        if (n == 1 && k == 1)
            return 0;
        int size = (int) Math.pow(2, n - 2);
        if (k <= size) {
            return kthGrammar(n - 1, k);
        } else {
            return kthGrammar(n - 1, k - size) ^ 1;
        }
    }
}
