package org.example.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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

    /*
        You are given an integer array nums and two integers minK and maxK.
        A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
            The minimum value in the subarray is equal to minK.
            The maximum value in the subarray is equal to maxK.
            Return the number of fixed-bound subarrays.
        A subarray is a contiguous part of an array.
    */
    public long countSubarrays(final int[] nums, final int minK, final int maxK) {
        int minI = -1, maxI = -1, left = -1, right = 0;
        long count = 0;
        while(right < nums.length){
            if(nums[right] < minK || nums[right] > maxK){
                minI = right;
                maxI = right;
                left = right;
            }
            minI = nums[right] == minK ? right : minI;
            maxI = nums[right] == maxK ?  right : maxI;
            count += Math.min(minI, maxI) - left;
            right++;
        }
        return count;
    }

    /*
        Given an array arr of positive integers sorted in a strictly increasing order,
        and an integer k.
        Return the kth positive integer that is missing from this array.
    */
    public int findKthPositive(final int[] arr, final  int k) {
        int lo =0;
        int hi = arr.length;
        while(lo < hi){
            final int mid = lo + (hi - lo) / 2;
            final int missing = arr[mid] - (mid+1);
            if(missing<k) lo = mid+1;
            else hi = mid;
        }
        return lo + k;
    }

    /*
        You are given a 0-indexed array nums comprising of n non-negative integers.
        In one operation, you must:
            Choose an integer i such that 1 <= i < n and nums[i] > 0.
            Decrease nums[i] by 1.
            Increase nums[i - 1] by 1.
        Return the minimum possible value of the maximum integer of nums after performing
            any number of operations.
    */
    public int minimizeArrayValue(final int[] nums) {
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans = Math.max(ans, (sum + i) / (i + 1));
        }
        return ans;
    }

    /*
    Given an array of integers nums, sort the array in ascending order and return it.
    You must solve the problem without using any built-in functions in O(nlog(n))
    time complexity and with the smallest space complexity possible.
     */
    public int[] sortArray(final int[] nums) {
        quickSort(nums,0, nums.length - 1);
        return nums;
    }

    private void quickSort(final int[] nums, final int s,final int e){
        if( s >= e) {
            return;
        }
        int p = nums[s];
        int curr = s+1, back=e;
        while(curr <= back) {
            if(p > nums[curr]) {
                curr++;
            } else if(p > nums[back]) {
                swap(nums, curr, back);
                back--;
            } else{
                back--;
            }
        }
        swap(nums, s, curr - 1);
        quickSort(nums, s, curr - 1);
        quickSort(nums, curr, e);
    }

    private void swap(final int[] nums, final int i, final int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
