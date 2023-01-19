package org.example;

import java.util.HashMap;
import java.util.Map;

public class SubarrayTasks {

    /*
    Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
    A circular array means the end of the array connects to the beginning of the array.
    Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
    A subarray may only include each element of the fixed buffer nums at most once.
    Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
    */
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

    /*
        Given an integer array nums and an integer k,
        return the number of non-empty subarrays that have a sum divisible by k.
        A subarray is a contiguous part of an array.
    */
    public int subarrayDivByK(int[] nums, int k) {
        final Map<Integer, Integer> map = new HashMap<>();
        int remainder;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                nums[i] += nums[i - 1];
            }
            remainder = (nums[i] % k + k) % k;
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }
        int count = map.getOrDefault(0, 0);
        for (int frequency : map.values()) {
            count += frequency * (frequency - 1) / 2;
        }
        return count;
    }
}
