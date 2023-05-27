package org.example.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Piles {

    /*
        Alice and Bob continue their games with piles of stones.
        There are a number of piles arranged in a row, and each pile has a positive integer number of
        stones piles[i].  The objective of the game is to end with the most stones.
        Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
        On each player's turn, that player can take all the stones in the first X remaining piles,
        where 1 <= X <= 2M.  Then, we set M = max(M, X).
        The game continues until all the stones have been taken.
        Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
    */
    public int stoneGameII(final int[] piles) {
        int[] postSum = new int[piles.length];
        final Map<String, Integer> cache = new HashMap<>();
        postSum[piles.length - 1] = piles[piles.length - 1];
        for (int i = piles.length - 2; i >= 0; i--) {
            postSum[i] = postSum[i + 1] + piles[i];
        }
        final int res = maxPiles(piles, 0, 1, postSum, cache);
        return (res + postSum[0]) / 2;
    }

    /*
        Alice and Bob continue their games with piles of stones.
        There are several stones arranged in a row, and each stone has an associated value
        which is an integer given in the array stoneValue.
        Alice and Bob take turns, with Alice starting first. On each player's turn,
        that player can take 1, 2, or 3 stones from the first remaining stones in the row.
        The score of each player is the sum of the values of the stones taken.
        The score of each player is 0 initially.
        The objective of the game is to end with the highest score, and the winner is the player
        with the highest score and there could be a tie. The game continues until all the stones
        have been taken.
        Assume Alice and Bob play optimally.
        Return "Alice" if Alice will win, "Bob" if Bob will win, or "Tie" if they will end
        the game with the same score.
    */
    public String stoneGameIII(final int[] stoneValue) {
        int DP[] = new int[stoneValue.length];
        Arrays.fill(DP, Integer.MIN_VALUE);
        int ans = Integer.MIN_VALUE;
        ans = Math.max(ans, DFS(0, 0, stoneValue, DP));
        if (stoneValue.length >= 2)
            ans = Math.max(ans, DFS(0, 1, stoneValue, DP));
        if (stoneValue.length >= 3)
            ans = Math.max(ans, DFS(0, 2, stoneValue, DP));
        if (ans == 0) return "Tie";
        return ans < 0 ? "Bob" : "Alice";
    }

    private int DFS(final int i, final int j, final int[] stoneValue, final int[] DP) {
        int bob = Integer.MIN_VALUE;
        if (DP[i] != Integer.MIN_VALUE) return DP[i];
        if (j + 1 < DP.length) {
            bob = Math.max(bob, DFS(j + 1, j + 1, stoneValue, DP));
        }
        if (j + 2 < DP.length) {
            bob = Math.max(bob, DFS(j + 1, j + 2, stoneValue, DP));
        }
        if (j + 3 < DP.length) {
            bob = Math.max(bob, DFS(j + 1, j + 3, stoneValue, DP));
        }
        int alice = 0;
        for (int k = i; k <= j; k++) {
            alice += stoneValue[k];
        }

        if (j + 1 < DP.length && bob != Integer.MIN_VALUE) {
            DP[j + 1] = bob;
        }

        return bob == Integer.MIN_VALUE ? alice : alice - bob;
    }


    private int maxPiles(final int[] piles,
                         final int start,
                         final int m,
                         final int[] postSum,
                         final Map<String, Integer> cache) {
        if (cache.containsKey(start + "," + m)) {
            return cache.get(start + "," + m);
        }
        final int x = 2 * m;
        final int range = piles.length - start;
        if (range <= x) {
            return postSum[start];
        }
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= x; i++) {
            final int sum = postSum[start] - postSum[start + i];
            res = Math.max(res, sum - maxPiles(piles, start + i, Math.max(i, m), postSum, cache));
        }
        cache.put(start + "," + m, res);
        return cache.get(start + "," + m);
    }

}
