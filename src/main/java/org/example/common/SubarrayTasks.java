package org.example.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
    public int subarrayDivByK(final int[] nums, final int k) {
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

    /*
        Given an integer array nums,
        return all the different possible non-decreasing subsequences of the given array
        with at least two elements. You may return the answer in any order.
    */
    public List<List<Integer>> findSubsequences(final int[] nums) {
        final List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        int size;
        final HashSet<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < (1 << len); i++) {
            List<Integer> list = new ArrayList<>();
            int a = i;
            for (final int num : nums) {
                size = list.size();
                if ((a & 1) == 1) {
                    if (size >= 1 && num < list.get(size - 1)) {
                        break;
                    }
                    list.add(num);
                }
                a = a >> 1;
                if (a == 0) {
                    break;
                }
            }
            if (list.size() >= 2) {
                if (!set.contains(list)) {
                    result.add(new ArrayList<>(list));
                    set.add(new ArrayList<>(list));
                }
            }
        }
        return result;
    }

    /*
        Given an integer array nums, return the number of subarrays filled with 0.
        A subarray is a contiguous non-empty sequence of elements within an array.
     */
    public long zeroFilledSubarray(final int[] nums) {
        long count = 0;
        long result = 0;
        for (final int num : nums) {
            if (num != 0) {
                count = 0;
            } else {
                count++;
            }
            result += count;
        }
        return result;
    }

    /*
        You are given a 0-indexed array nums of n integers, and an integer k.
        The k-radius average for a subarray of nums centered at some index i with the radius k
        is the average of all elements in nums between the indices i - k and i + k (inclusive).
        If there are less than k elements before or after the index i, then the k-radius average is -1.
        Build and return an array avgs of length n where avgs[i] is the k-radius average for
        the subarray centered at index i.
        The average of x elements is the sum of the x elements divided by x, using integer division.
        The integer division truncates toward zero, which means losing its fractional part.
        For example, the average of four elements 2, 3, 1, and 5 is (2 + 3 + 1 + 5) / 4 = 11 / 4 = 2.75,
         which truncates to 2.
    */
    public int[] getAverages(final int[] nums, final int k) {
        final int n = nums.length;
        long winSum = 0;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        for (int i = 0; i < Math.min((2 * k), n); i++) {
            winSum += nums[i];
        }
        for (int i = k; i < n - k; i++) {
            winSum += nums[i + k];
            result[i] = (int) (winSum / (k + k + 1));
            winSum -= nums[i - k];
        }
        return result;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        permuteRec(nums, 0, result);
        return result;
    }

    /*
        Given an array nums of distinct integers, return all the possible permutations.
        You can return the answer in any order.
    */
    private void permuteRec(int[] nums, int begin, List<List<Integer>> result) {
        if (begin == nums.length) {
            List<Integer> temp = new ArrayList<Integer>();
            for (int num : nums) temp.add(num);
            result.add(temp);
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            // Swap
            int temp = nums[begin];
            nums[begin] = nums[i];
            nums[i] = temp;

            permuteRec(nums, begin + 1, result);

            // Swap back
            temp = nums[begin];
            nums[begin] = nums[i];
            nums[i] = temp;
        }
    }
}
