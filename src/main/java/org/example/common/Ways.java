package org.example.common;

import java.util.Arrays;

/*
    You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position
    to the left, 1 position to the right in the array, or stay in the same place (The pointer should
    not be placed outside the array at any time).
    Given two integers steps and arrLen, return the number of ways such that your pointer is still
    at index 0 after exactly steps. Since the answer may be too large, return it modulo 10^9 + 7.
*/
public class Ways {

    private static final int MOD = 1_000_000_007;

    public int numWays(int steps, int arrLen) {
        arrLen = Math.min(steps / 2 + 1, arrLen);
        int[][] dp = new int[arrLen][steps + 1];
        for (final int[] ints : dp) Arrays.fill(ints, -1);
        return countWays(steps, 0, dp);
    }

    private static int countWays(int steps, int pos, int[][] dp) {
        if (steps < pos) return 0;
        if (steps == 0) return 1;
        if (dp[pos][steps] != -1) return dp[pos][steps];
        int ways = 0;
        if (pos < dp.length - 1) ways = (ways + countWays(steps - 1, pos + 1, dp)) % MOD;
        if (pos > 0) ways = (ways + countWays(steps - 1, pos - 1, dp)) % MOD;
        ways = (ways + countWays(steps - 1, pos, dp)) % MOD;
        dp[pos][steps] = ways;
        return ways;
    }

}
