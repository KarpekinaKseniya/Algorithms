package org.example;

/*
    You are given a 0-indexed array of integers nums of length n.
    You are initially positioned at nums[0].
    Each element nums[i] represents the maximum length of a forward jump from index i.
    In other words, if you are at nums[i], you can jump to any nums[i + j] where:
        0 <= j <= nums[i] and
        i + j < n
    Return the minimum number of jumps to reach nums[n - 1].
    The test cases are generated such that you can reach nums[n - 1].
*/
public class JumpGame {

    public int jump(final int[] nums) {
        int score = 0;
        int num = 0;
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == num) {
                score++;
                num = max;
            }
        }
        return score;
    }
}
