package org.example;

import java.util.Arrays;

/*
    You are the manager of a basketball team.
    For the upcoming tournament, you want to choose the team with the highest overall score.
    The score of the team is the sum of scores of all the players in the team.

    However, the basketball team is not allowed to have conflicts.
    A conflict exists if a younger player has a strictly higher score than an older player.
    A conflict does not occur between players of the same age.

    Given two lists, scores and ages, where each scores[i] and ages[i] represents
    the score and age of the ith player, respectively, return the highest overall score of all possible
    basketball teams.
*/
public class Team {
    public int bestTeamScore(final int[] scores, final int[] ages) {
        final int n = ages.length;
        int[][] candidate = new int[n][2];
        for (int i = 0; i < n; i++) {
            candidate[i][0] = ages[i];
            candidate[i][1] = scores[i];
        }
        Arrays.sort(candidate, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] dp = new int[n];
        dp[0] = candidate[0][1];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = candidate[i][1];
            for (int j = 0; j < i; j++) {
                if (candidate[j][1] <= candidate[i][1]) {
                    dp[i] = Math.max(dp[i], candidate[i][1] + dp[j]);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
