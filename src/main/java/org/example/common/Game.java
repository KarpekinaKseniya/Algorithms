package org.example.common;

public class Game {

    /*
        Alice plays the following game, loosely based on the card game "21".
        Alice starts with 0 points and draws numbers while she has less than k points.
        During each draw, she gains an integer number of points randomly from the range [1, maxPts],
        where maxPts is an integer. Each draw is independent and the outcomes have equal probabilities.
        Alice stops drawing numbers when she gets k or more points.
        Return the probability that Alice has n or fewer points.
        Answers within 10-5 of the actual answer are considered accepted.
    */
    public double new21Game(final int n, final int k, final int maxPts) {
        if (k == 0 || n >= k + maxPts) {
            return 1.0;
        }
        double[] dp = new double[n + 1];
        double windowSum = 1.0;
        double probability = 0.0;
        dp[0] = 1.0;
        for (int i = 1; i <= n; i++) {
            dp[i] = windowSum / maxPts;
            if (i < k) {
                windowSum += dp[i];
            } else {
                probability += dp[i];
            }
            if (i - maxPts >= 0) {
                windowSum -= dp[i - maxPts];
            }
        }

        return probability;
    }

    /*
        There are n pieces arranged in a line, and each piece is colored either by 'A' or by 'B'. You are
         given a string colors of length n where colors[i] is the color of the ith piece.
        Alice and Bob are playing a game where they take alternating turns removing pieces from the line.
        In this game, Alice moves first.
        Alice is only allowed to remove a piece colored 'A' if both its neighbors are also colored 'A'.
        She is not allowed to remove pieces that are colored 'B'.
        Bob is only allowed to remove a piece colored 'B' if both its neighbors are also colored 'B'.
        He is not allowed to remove pieces that are colored 'A'.
        Alice and Bob cannot remove pieces from the edge of the line.
        If a player cannot make a move on their turn, that player loses and the other player wins.
        Assuming Alice and Bob play optimally, return true if Alice wins, or return false if Bob wins.
    */
    public boolean winnerOfGame(String colors) {
        return colors.replaceAll("A{3,}", "AA").length() <
                colors.replaceAll("B{3,}", "BB").length();
    }
}
