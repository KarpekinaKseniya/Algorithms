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

}
