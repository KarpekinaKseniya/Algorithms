package org.example.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    /*
        Given an integer array arr and an integer difference, return the length of the longest
        subsequence in arr which is an arithmetic sequence such that the difference between adjacent
        elements in the subsequence equals difference.
        A subsequence is a sequence that can be derived from arr by deleting some or no elements
        without changing the order of the remaining elements.
    */
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> nm = new HashMap<>();
        nm.put(arr[0], 1);
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i] - difference;
            if (nm.containsKey(value)) {
                nm.put(arr[i], nm.get(value) + 1);
            } else {
                nm.put(arr[i], 1);
            }
        }
        int result = 1;
        for (int i : nm.keySet()) {
            result = Math.max(result, nm.get(i));
        }
        return result;
    }

    /*
        Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
        A subsequence of a string is a new string that is formed from the original string by
        deleting some (can be none) of the characters without disturbing the relative positions
        of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
    */
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length())
            if (s.charAt(i) == t.charAt(j++))
                i++;
        return i == s.length();
    }
}
