package org.example;

import java.util.HashMap;
import java.util.Map;

/*
    Given an array nums of size n, return the majority element.

    The majority element is the element that appears more than n / 2 times.
    You may assume that the majority element always exists in the array.
*/
class WorkWithArrays {

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
}
