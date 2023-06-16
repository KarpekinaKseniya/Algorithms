package org.example.common;

import java.util.ArrayList;
import java.util.List;

/*
    Given an array nums that represents a permutation of integers from 1 to n.
    We are going to construct a binary search tree (BST) by inserting the elements of nums
    in order into an initially empty BST. Find the number of different ways to reorder nums so
    that the constructed BST is identical to that formed from the original array nums.
    For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right
    child. The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.
    Return the number of ways to reorder nums such that the BST formed is identical to the
    original BST formed from nums.
    Since the answer may be very large, return it modulo 109 + 7.
*/
public class ReorderWays {

    private static final int MOD = (int) (1e9 + 7);

    public int numOfWays(final int[] nums) {
        final int n = nums.length;
        long[][] combinations = new long[n + 1][n + 1];
        calculateCombinations(combinations);

        final List<Integer> list = new ArrayList<>();
        for (final int num : nums) {
            list.add(num);
        }

        return (int) (countWays(list, combinations) - 1);
    }

    private void calculateCombinations(final long[][] combinations) {
        final int n = combinations.length;
        for (int i = 0; i < n; i++) {
            combinations[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                combinations[i][j] = (combinations[i - 1][j] + combinations[i - 1][j - 1]) % MOD;
            }
        }
    }

    private long countWays(final List<Integer> nums, final long[][] combinations) {
        if (nums.size() <= 2) {
            return 1;
        }

        final int root = nums.get(0);
        final List<Integer> left = new ArrayList<>();
        final List<Integer> right = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        final long leftWays = countWays(left, combinations);
        final long rightWays = countWays(right, combinations);

        return (combinations[left.size() + right.size()][left.size()] * (leftWays % MOD) % MOD * (rightWays % MOD) % MOD);
    }
}
