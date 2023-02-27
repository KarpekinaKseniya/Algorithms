package org.example;

import java.util.*;

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

    /*
        You are given a sorted array consisting of only integers where every element appears exactly twice,
        except for one element which appears exactly once.
        Return the single element that appears only once.
    */
    public int singleNonDuplicate(final int[] nums) {
        final Map<Integer, Integer> map = new HashMap<>();
        for (final int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map.entrySet().stream()
                .filter(value -> value.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst().orElse(-1);
    }

    /*
        You are given an array nums of n positive integers.
        You can perform two types of operations on any element of the array any number of times:
        If the element is even, divide it by 2.
        For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
        If the element is odd, multiply it by 2.
        For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].
        The deviation of the array is the maximum difference between any two elements in the array.
        Return the minimum deviation the array can have after performing some number of operations.
    */
    public int minimumDeviation(final int[] nums) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int min = Integer.MAX_VALUE;
        for(int i : nums) {
            if(i % 2 == 1) {i *= 2;}

            pq.add(i);
            min = Math.min(min, i);
        }
        int diff = Integer.MAX_VALUE;
        while(pq.peek() % 2 == 0) {
            int max = pq.remove();
            diff = Math.min(diff, max - min);

            max /= 2;
            min = Math.min(min, max);
            pq.add(max);
        }
        return Math.min(diff, pq.peek() - min);
    }

    /*
        Given a 0-indexed integer array nums, find a 0-indexed integer array answer where:
            answer.length == nums.length.
            answer[i] = |leftSum[i] - rightSum[i]|.
        Where:
            leftSum[i] is the sum of elements to the left of the index i in the array nums.
            If there is no such element, leftSum[i] = 0.
            rightSum[i] is the sum of elements to the right of the index i in the array nums.
            If there is no such element, rightSum[i] = 0.
Return the array answer.
    */
    public int[] leftRightDifference(final int[] nums) {
        int leftSum = 0, rightSum = 0, len = nums.length;
        for(final int num : nums) {
            rightSum += num;
        }
        for(int i = 0; i < len; i++) {
            int val = nums[i];
            rightSum -= val;
            nums[i] = Math.abs(leftSum - rightSum);
            leftSum += val;
        }
        return nums;
    }
}
