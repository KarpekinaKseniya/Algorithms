package org.example.common;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
    You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive
    integer k. You must choose a subsequence of indices from nums1 of length k.
    For chosen indices i0, i1, ..., ik - 1, your score is defined as:
        The sum of the selected elements from nums1 multiplied with the minimum of the selected
        elements from nums2.
        It can defined simply as:
            (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
    Return the maximum possible score.
    A subsequence of indices of an array is a set that can be derived from
    the set {0, 1, ..., n-1} by deleting some or no elements.
 */
public class Score {

    public long maxScore(final int[] nums1, final int[] nums2, final int k) {
        final int len = nums1.length;
        int[][] arr = new int[len][2];
        for (int i = 0; i < len; i++) {
            arr[i] = new int[]{nums1[i], nums2[i]};
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(b[1], a[1]));
        long res = 0, sum = 0;
        Queue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            heap.offer(arr[i][0]);
            sum += arr[i][0];

            if (i >= k)
                sum -= heap.poll();

            if (i >= k - 1)
                res = Math.max(res, sum * arr[i][1]);
        }
        return res;
    }
}
