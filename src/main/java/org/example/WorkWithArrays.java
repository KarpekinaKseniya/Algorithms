package org.example;

import java.util.HashMap;
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
}
