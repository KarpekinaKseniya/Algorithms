package org.example.common;

import java.util.Arrays;

public class Ways {

    private static final int MOD = 1_000_000_007;

    /*
        You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position
        to the left, 1 position to the right in the array, or stay in the same place (The pointer should
        not be placed outside the array at any time).
        Given two integers steps and arrLen, return the number of ways such that your pointer is still
        at index 0 after exactly steps. Since the answer may be too large, return it modulo 10^9 + 7.
    */
    public int numWays(int steps, int arrLen) {
        arrLen = Math.min(steps / 2 + 1, arrLen);
        int[][] dp = new int[arrLen][steps + 1];
        for (final int[] ints : dp) Arrays.fill(ints, -1);
        return countWays(steps, 0, dp);
    }

    /*
        Along a long library corridor, there is a line of seats and decorative plants. You are given
        a 0-indexed string corridor of length n consisting of letters 'S' and 'P' where each 'S'
        represents a seat and each 'P' represents a plant.
        One room divider has already been installed to the left of index 0, and another to the right
        of index n - 1. Additional room dividers can be installed. For each position between
        indices i - 1 and i (1 <= i <= n - 1), at most one divider can be installed.
        Divide the corridor into non-overlapping sections, where each section has exactly two seats
        with any number of plants. There may be multiple ways to perform the division. Two ways are
        different if there is a position with a room divider installed in the first way but not in
        the second way.
        Return the number of ways to divide the corridor. Since the answer may be very large, return it
        modulo 10^9 + 7. If there is no way, return 0.
    */
    public int numberOfWays(String corridor) {
        final int n = corridor.length();
        final byte[] corr = new byte[n];
        corridor.getBytes(0, n, corr, 0);
        long ways = 1;
        int seatCount = 0;
        int prevSeatIdx = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (corr[i] == 'P') continue;
            if (seatCount != 0)
                prevSeatIdx = i;
            else if (prevSeatIdx > 0)
                ways = (ways * (prevSeatIdx - i)) % MOD;
            seatCount ^= 1;
        }
        return (prevSeatIdx < 0 || seatCount != 0) ? 0 : (int) ways;
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
