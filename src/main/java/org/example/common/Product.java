package org.example.common;

/*
    There is a function signFunc(x) that returns:
        1 if x is positive.
        -1 if x is negative.
        0 if x is equal to 0.
    You are given an integer array nums. Let product be the product of all values in the array nums.
    Return signFunc(product).
*/
public class Product {

    public int arraySign(final int[] nums) {
        int countMinus = 0;
        for (final int num : nums) {
            if (num == 0) {
                return 0;
            }
            if (num < 0) {
                countMinus++;
            }
        }
        return countMinus % 2 == 0 ? 1 : -1;
    }

}
