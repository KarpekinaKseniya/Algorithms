package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WorkWithArrays {

    /*
    Given an array nums of size n, return the majority element.

    The majority element is the element that appears more than n / 2 times.
    You may assume that the majority element always exists in the array.
    */
    public int findMajorityElement(final int[] nums) {
        final int appears = nums.length / 2;
        final Map<Integer, Integer> map = new HashMap<>();
        for (final int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        //@formatter:off
        return map.entrySet().stream()
                .filter(value -> value.getValue() > appears)
                .map(Map.Entry::getKey)
                .findFirst().orElseThrow(() -> new RuntimeException("Majority element not found"));
        //@formatter:off
    }

    /*
    Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
    Return the array in the form [x1,y1,x2,y2,...,xn,yn].
    */
    public int[] shuffle(final int[] nums, final int n) {
        int[] result = new int[nums.length];
        int j = 0;
        for (int i = 0; i < n; i++) {
            result[j++] = nums[i];
            result[j++] = nums[i + n];
        }
        return result;
    }

    /*
        Given an array of integers nums sorted in non-decreasing order,
        find the starting and ending position of a given target value.
        If target is not found in the array, return [-1, -1].
        You must write an algorithm with O(log n) runtime complexity.
    */
    public int[] searchRange(final int[] nums, final int target) {
        int[] result = {-1, -1};
        final List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            final int num = nums[i];
            if (num == target){
                indexes.add(i);
            }
        }
        if (indexes.isEmpty()) {
            return result;
        }
        result[0] = indexes.get(0);
        if (indexes.size() > 1) {
            result[1] = indexes.get(indexes.size() - 1);
        } else {
            result[1] = result[0];
        }
        return result;
    }
}
