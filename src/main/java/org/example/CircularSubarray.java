package org.example;

/*
    Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
    A circular array means the end of the array connects to the beginning of the array.
    Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
    A subarray may only include each element of the fixed buffer nums at most once.
    Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
*/
public class CircularSubarray {

    public int maxSubarraySumCircular(final int[] nums) {
        int os = nums[0];
        int min = nums[0];
        int res = nums[0];
        int min2 = nums[0];
        int res2 = nums[0];
        for (int i = 1; i < nums.length; i++) {
            os += nums[i];
            min = Math.min(nums[i] + min, nums[i]);
            res = Math.min(res, min);
            min2 = Math.max(nums[i] + min2, nums[i]);
            res2 = Math.max(res2, min2);
        }
        if (os == res) {
            return res2;
        }
        return Math.max(os - res, res2);
    }
}
