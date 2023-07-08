package org.example.common;

import java.util.Arrays;

/*
    You have k bags. You are given a 0-indexed integer array weights where weights[i]
    is the weight of the ith marble. You are also given the integer k.
    Divide the marbles into the k bags according to the following rules:
        No bag is empty.
        If the ith marble and jth marble are in a bag, then all marbles with an
         index between the ith and jth indices should also be in that same bag.
        If a bag consists of all the marbles with an index from i to j inclusively,
        then the cost of the bag is weights[i] + weights[j].
        The score after distributing the marbles is the sum of the costs of all the k bags.
    Return the difference between the maximum and minimum scores among marble distributions.
*/
public class Marbles {

    public long putMarbles(final int[] weights, final int k) {
        final int n = weights.length;
        if (k == n) return 0;
        int[] costs = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            costs[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(costs);
        long min = 0, max = 0;
        for (int i = 0; i < k - 1; i++) {
            min += costs[i];
            max += costs[n - i - 2];
        }
        return max - min;
    }

}
