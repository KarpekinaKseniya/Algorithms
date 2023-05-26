package org.example.common;

import java.util.HashMap;
import java.util.Map;

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
public class Piles {

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
