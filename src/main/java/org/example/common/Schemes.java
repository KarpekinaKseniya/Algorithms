package org.example.common;

/*
    There is a group of n members, and a list of various crimes they could commit.
    The ith crime generates a profit[i] and requires group[i] members to participate in it.
    If a member participates in one crime, that member can't participate in another crime.

    Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit,
    and the total number of members participating in that subset of crimes is at most n.

    Return the number of schemes that can be chosen. Since the answer may be very large, return it
    modulo 10^9 + 7.
*/
public class Schemes {

    public int profitableSchemes(final int n, final int minProfit, final int[] group, final int[] profit) {
        final int len = group.length;
        final int MOD = (int) 1e9 + 7;
        int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= len; i++) {
            final int members = group[i - 1], earn = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < members) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - members][Math.max(0, k - earn)]) % MOD;
                    }
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= n; j++) {
            sum = (sum + dp[len][j][minProfit]) % MOD;
        }
        return sum;
    }

}
