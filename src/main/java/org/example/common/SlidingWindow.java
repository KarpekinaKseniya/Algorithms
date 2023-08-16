package org.example.common;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindow {

    /*
        You are given an array of integers nums, there is a sliding window of size k which is moving
        from the very left of the array to the very right. You can only see the k numbers in the window.
        Each time the sliding window moves right by one position.
        Return the max sliding window.
    */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        int r = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!dq.isEmpty() && dq.peek() == i - k) {
                dq.poll();
            }
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            dq.offer(i);
            if (i >= k - 1) {
                res[r++] = nums[dq.peek()];
            }

        }
        return res;
    }

}
