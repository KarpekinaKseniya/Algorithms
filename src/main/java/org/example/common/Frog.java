package org.example.common;

public class Frog {

    /*
        A frog is crossing a river. The river is divided into some number of units,
        and at each unit, there may or may not exist a stone. The frog can jump on a stone,
        but it must not jump into the water.
        Given a list of stones' positions (in units) in sorted ascending order, determine if the frog
        can cross the river by landing on the last stone. Initially, the frog is on the first stone
        and assumes the first jump must be 1 unit.
        If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units.
        The frog can only jump in the forward direction.
    */
    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n + 1];
        dp[0][1] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int jump = stones[i] - stones[j];
                if (jump <= j + 1) {
                    dp[i][jump] = dp[j][jump - 1] || dp[j][jump] || dp[j][jump + 1];
                    if (i == n - 1 && dp[i][jump]) return true;
                }
            }
        }
        return false;
    }

}
