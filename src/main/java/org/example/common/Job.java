package org.example.common;

public class Job {

    /*
        You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job,
        you have to finish all the jobs j where 0 <= j < i).
        You have to finish at least one task every day. The difficulty of a job schedule is the sum of
        difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of
        a job done on that day.
        You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job
        is jobDifficulty[i].
        Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs
        return -1.
    */
    public int minDifficulty(int[] jobDifficulty, int days) {
        if (days > jobDifficulty.length) {
            return -1;
        }
        int[][] dp = new int[jobDifficulty.length + 1][days + 1];
        for (int idx = jobDifficulty.length - 1; idx >= 0; idx--) {
            for (int d = days; d >= 0; d--) {
                if (idx >= jobDifficulty.length) {
                    if (d == 0) {
                        dp[idx][d] = 0;
                        continue;
                    } else {
                        dp[idx][d] = (int) 1e8;
                        continue;
                    }
                } else if (d == 0) {
                    dp[idx][d] = (int) 1e8;
                    continue;
                }
                int ans = (int) 1e8;
                int max = -1;
                for (int i = idx; i <= jobDifficulty.length - d; i++) {
                    max = Math.max(max, jobDifficulty[i]);
                    int temp = max + dp[i + 1][d - 1];
                    ans = Math.min(temp, ans);
                }
                dp[idx][d] = ans;
            }
        }
        return dp[0][days];
    }

}
