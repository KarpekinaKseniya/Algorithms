package org.example.common;

/*
    You are given a 0-indexed integer array nums. You have to partition the array into one
    or more contiguous subarrays.
    We call a partition of the array valid if each of the obtained subarrays satisfies one
    of the following conditions:
        The subarray consists of exactly 2 equal elements. For example, the subarray [2,2] is good.
        The subarray consists of exactly 3 equal elements. For example, the subarray [4,4,4] is good.
        The subarray consists of exactly 3 consecutive increasing elements, that is,
            the difference between adjacent elements is 1. For example, the subarray [3,4,5] is good,
            but the subarray [1,3,5] is not.
    Return true if the array has at least one valid partition. Otherwise, return false.
*/
public class Partition {

    public boolean validPartition(int[] nums) {
        int n = nums.length;
        Boolean[] dp = new Boolean[n];
        return f(n - 1, nums, dp);
    }

    private boolean f(int i, int[] nums, Boolean[] dp) {
        if (i < 0)
            return true;
        if (dp[i] != null)
            return dp[i];

        boolean equal2 = false, equal3 = false, increasing3 = false;
        if (i > 0 && nums[i] == nums[i - 1])
            equal2 = f(i - 2, nums, dp);
        if (i > 1 && nums[i] == nums[i - 1] && nums[i] == nums[i - 2])
            equal3 = f(i - 3, nums, dp);
        if (i > 1 && nums[i] == nums[i - 1] + 1 && nums[i - 1] == nums[i - 2] + 1)
            increasing3 = f(i - 3, nums, dp);
        return dp[i] = equal2 || equal3 || increasing3;
    }

}
