package org.example.common;

import java.util.TreeMap;

public class Cost {

    /*
        You are given two 0-indexed arrays nums and cost consisting each of n positive integers.
        You can do the following operation any number of times:
            Increase or decrease any element of the array nums by 1.
            The cost of doing one operation on the ith element is cost[i].
        Return the minimum total cost such that all the elements of the array nums become equal.
    */
    public long minCost(int[] nums, int[] cost) {
        final TreeMap<Integer, Long> numCosts = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            numCosts.put(nums[i], numCosts.getOrDefault(nums[i], (long) 0) + cost[i]);
        }
        long preSum = 0;  // exclusive
        long postSum = 0;  // inclusive
        for (int n : numCosts.keySet()) {
            postSum += numCosts.get(n);
        }
        int target = numCosts.firstKey();
        long currCost = 0;
        for (int n : numCosts.keySet()) {
            currCost += Math.abs(n - target) * numCosts.get(n);
        }
        long minCost = currCost;
        while (numCosts.higherKey(target) != null) {
            int nextTarget = numCosts.higherKey(target);
            preSum += numCosts.get(target);
            postSum -= numCosts.get(target);
            currCost += Math.abs(nextTarget - target) * (preSum - postSum);
            minCost = Math.min(minCost, currCost);
            target = nextTarget;
        }
        return minCost;
    }

    /*
        Given n orders, each order consist in pickup and delivery services.
        Count all valid pickup/delivery possible sequences such that delivery(i)
            is always after of pickup(i).
        Since the answer may be too large, return it modulo 10^9 + 7.
    */
    public int countOrders(int n) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            long k = (long) i * (i + (i - 1));
            dp[i] = (dp[i - 1] * k) % 100_000_000_7;
        }
        return (int) dp[dp.length - 1];
    }

    /*
        There are 3n piles of coins of varying size, you and your friends will take piles of coins
        as follows:
            In each step, you will choose any 3 piles of coins (not necessarily consecutive).
            Of your choice, Alice will pick the pile with the maximum number of coins.
            You will pick the next pile with the maximum number of coins.
            Your friend Bob will pick the last pile.
            Repeat until there are no more piles of coins.
            Given an array of integers piles where piles[i] is the number of coins in the ith pile.
        Return the maximum number of coins that you can have.
    */
    public int maxCoins(int[] piles) {
        int maxCoins = 0;
        int[] freq = new int[100001];
        for (int i : piles) {
            freq[i]++;
        }
        int chances = piles.length / 3;
        int turn = 1;
        int i = freq.length - 1;
        while (chances != 0) {
            if (freq[i] > 0) {
                if (turn == 1) {
                    turn = 0;
                } else {
                    maxCoins += i;
                    turn = 1;
                    chances--;
                }

                freq[i]--;
            } else {
                i--;
            }
        }
        return maxCoins;
    }
}
