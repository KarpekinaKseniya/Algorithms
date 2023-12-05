package org.example.common;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Team {

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

    /*
    You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.
    You are also given two integers k and candidates. We want to hire exactly k workers according
    to the following rules:
    You will run k sessions and hire exactly one worker in each session.
    In each hiring session, choose the worker with the lowest cost from either the first candidates
    workers or the last candidates workers. Break the tie by the smallest index.
    For example, if costs = [3,2,7,7,1,2] and candidates = 2, then in the first hiring session,
    we will choose the 4th worker because they have the lowest cost [3,2,7,7,1,2].
    In the second hiring session, we will choose 1st worker because they have the same lowest
    cost as 4th worker but they have the smallest index [3,2,7,7,2].
    Please note that the indexing may be changed in the process.
    If there are fewer than candidates workers remaining, choose the worker with the lowest cost
    among them. Break the tie by the smallest index.
    A worker can only be chosen once.
    Return the total cost to hire exactly k workers.
     */
    public long totalCost(int[] costs, int k, int candidates) {
        final int n = costs.length;
        final Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        if (2 * candidates >= n) {
            for (final int cost : costs) queue.offer(new int[]{cost, 0});
        } else {
            for (var i = 0; i < candidates; i++) {
                queue.offer(new int[]{costs[i], 0});
                queue.offer(new int[]{costs[n - 1 - i], 1});
            }
        }
        int l = candidates, r = n - 1 - candidates;
        long cost = 0L;
        for (int i = 0; i < k; i++) {
            int[] a = queue.poll();
            cost += a[0];
            if (l > r) continue;
            if (a[1] == 0)
                queue.offer(new int[]{costs[l++], 0});
            else
                queue.offer(new int[]{costs[r--], 1});
        }
        return cost;
    }

    /*
        You are given an integer n, the number of teams in a tournament that has strange rules:
            If the current number of teams is even, each team gets paired with another team.
                A total of n / 2 matches are played, and n / 2 teams advance to the next round.
            If the current number of teams is odd, one team randomly advances in the tournament,
                and the rest gets paired. A total of (n - 1) / 2 matches are played, and (n - 1) / 2 + 1
                teams advance to the next round.
        Return the number of matches played in the tournament until a winner is decided.
    */
    public int numberOfMatches(int n) {
        int sum = 0;
        int team = n;
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = (n - 1) / 2 + 1;
            }
            sum += team - n;
            team = n;
        }
        return sum;
    }
}
