package org.example.common;

public class Stairs {

    /*
        You are climbing a staircase. It takes n steps to reach the top.
        Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
    */
    public int climbStairs(final int n) {
        if (n <= 1) {
            return 1;
        }
        int prev1 = 1;
        int prev2 = 2;
        for (int i = 3; i <= n; i++) {
            int val = prev1 + prev2;
            prev1 = prev2;
            prev2 = val;
        }
        return prev2;
    }

    /*
        You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
        Once you pay the cost, you can either climb one or two steps.
        You can either start from the step with index 0, or the step with index 1.
        Return the minimum cost to reach the top of the floor.
    */
    public int minCostClimbingStairs(final int[] cost) {
        final int len = cost.length;
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < len; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[len - 1], dp[len - 2]);
    }
}
