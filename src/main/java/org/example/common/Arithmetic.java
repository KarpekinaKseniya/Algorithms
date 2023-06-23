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

}
