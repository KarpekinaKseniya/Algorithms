package org.example;

/*
Given an integer array nums, move all 0's to the end of it while
maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.
 */
public class MoveZeroes {

    public void moveZeroes(final int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return;
        }
        while (len != 0) {
            for (int i = 0; i < len - 1; i++) {
                if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                }
            }
            len--;
        }
    }
}
