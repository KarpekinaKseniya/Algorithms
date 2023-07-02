package org.example.common;

import java.util.Arrays;

/*
    We have n buildings numbered from 0 to n - 1.
    Each building has a number of employees.
    It's transfer season, and some employees want to change the building they reside in.
    You are given an array requests where requests[i] = [from-i, to-i] represents an
    employee's request to transfer from building from-i to building to-i.
    All buildings are full, so a list of requests is achievable only if for each building,
    the net change in employee transfers is zero. This means the number of employees leaving
    is equal to the number of employees moving in. For example if n = 3 and two employees
    are leaving building 0, one is leaving building 1, and one is leaving building 2,
    there should be two employees moving to building 0, one employee moving to building 1,
    and one employee moving to building 2.
    Return the maximum number of achievable requests.
*/
public class AchievableTransfer {

    public int maximumRequests(final int n, final int[][] requests) {
        int bits = (1 << requests.length) - 1;
        int result = 0;

        for (int i = 1; i <= bits; i++) {
            int[] dp = new int[n];
            for (int j = 0; j < requests.length; j++) {
                if (((i >> j) & 1) == 1) {
                    dp[requests[j][0]]--;
                    dp[requests[j][1]]++;
                }
            }

            if (Arrays.equals(dp, new int[n])) result = Math.max(result, Integer.bitCount(i));
        }

        return result;
    }

}
