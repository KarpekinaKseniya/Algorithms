package org.example.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Sums {

    /*
        Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such
        that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
        Notice that the solution set must not contain duplicate triplets.
    */
    public List<List<Integer>> threeSum(final int[] nums) {
        final HashSet<ArrayList<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            final int target = -nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    j++;
                    k--;
                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    j++;
                }

            }
        }
        return new ArrayList<>(res);
    }

    /*
        You are given a positive integer array nums.
        The element sum is the sum of all the elements in nums.
        The digit sum is the sum of all the digits (not necessarily distinct) that appear in nums.
        Return the absolute difference between the element sum and digit sum of nums.
        Note that the absolute difference between two integers x and y is defined as |x - y|.
    */
    public int differenceOfSum(final int[] nums) {
        final int sum = arraySum(nums);
        final int digitsSum = digitSum(nums);
        return Math.abs(sum - digitsSum);
    }

    private int arraySum(final int[] nums) {
        int sum = 0;
        for (final int num : nums) {
            sum += num;
        }
        return sum;
    }

    private int digitSum(final int[] nums) {
        int sum = 0;
        for (final int num : nums) {
            if (num <= 9) {
                sum += num;
            } else {
                int digits = num;
                while (digits > 0) {
                    sum = sum + digits % 10;
                    digits = digits / 10;
                }
            }
        }
        return sum;
    }
}
