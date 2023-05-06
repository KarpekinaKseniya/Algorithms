package org.example.common;

import java.util.Arrays;

public class Sequences {

    /*
        Given two integer arrays pushed and popped each with distinct values,
        return true if this could have been the result of a sequence of push and pop operations
        on an initially empty stack, or false otherwise.
    */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int pushedI = 0;
        int poppedI = 0;
        for (final int elm : pushed) {
            pushed[pushedI++] = elm;
            while (pushedI != 0 && popped[poppedI] == pushed[pushedI - 1]) {
                poppedI++;
                pushedI--;
            }
        }
        return pushedI == 0;
    }

    /*
        You are given an array of integers nums and an integer target.
        Return the number of non-empty subsequences of nums such that the sum of the minimum and
        maximum element on it is less or equal to target. Since the answer may be too large, return
        it modulo 10^9 + 7.
    */
    public int numSubseq(final int[] nums, final int target) {
        final int mod = 1000000007;
        final int n = nums.length;
        Arrays.sort(nums);
        int[] exp = new int[n];
        exp[0] = 1;
        for (int i = 1; i < n; i++) {
            exp[i] = (exp[i - 1] * 2) % mod;
        }
        int i = 0;
        int j = n - 1;
        int cnt = 0;
        while (i <= j) {
            if (nums[i] + nums[j] <= target) {
                cnt = (cnt + exp[j - i]) % mod;
                i++;
            } else {
                j--;
            }
        }
        return cnt;
    }
}
