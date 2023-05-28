package org.example.common;

import java.util.Arrays;

/*
    Given a wooden stick of length n units. The stick is labelled from 0 to n.
    Return the minimum total cost of the cuts.
*/
public class Stick {

    public int minCost(final int n, final int[] cuts) {
        int[] c = new int[cuts.length + 2];
        for (int i = 0; i < cuts.length; i++) {
            c[i + 1] = cuts[i];
        }
        c[c.length - 1] = n;
        Arrays.sort(c);
        int[][] dp = new int[c.length][c.length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                for (int k = i - 1; k > j; k--) {
                    final int ans = Math.min(dp[j][i] == 0 ? Integer.MAX_VALUE : dp[j][i], c[i] - c[j] + dp[j][k] + dp[k][i]);
                    dp[j][i] = ans;

                }
            }
        }
        return dp[0][c.length - 1];
    }

}
