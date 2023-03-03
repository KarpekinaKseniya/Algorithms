package org.example.common;

/*
    You are climbing a staircase. It takes n steps to reach the top.
    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/
public class Stairs {
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
}
