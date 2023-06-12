package org.example.common;

import java.util.ArrayList;
import java.util.List;

public class Range {

    /*
        You are given a sorted unique integer array nums.
        A range [a,b] is the set of all integers from a to b (inclusive).
        Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
        That is, each element of nums is covered by exactly one of the ranges, and there is
        no integer x such that x is in one of the ranges but not in nums.
        Each range [a,b] in the list should be output as:
            "a->b" if a != b
            "a" if a == b
    */
    public List<String> summaryRanges(final int[] nums) {
        final List<String> list = new ArrayList<>();
        final int len = nums.length;
        int i = 0;
        while (i < len) {
            int j = i;
            while (j < len - 1 && nums[j + 1] == nums[j] + 1) {
                j++;
            }
            if (i == j) {
                list.add(nums[i] + "");
            } else {
                list.add(nums[i] + "->" + nums[j]);
            }
            i = j + 1;
        }
        return list;
    }

}
