package org.example;

public class NumberTheory {

    /*
        Given an integer array nums, return the greatest common divisor of the smallest number and largest number in nums.
        The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.
     */
    public int findGCD(final int[] nums) {
        int min = nums[0];
        int max = nums[0];
        for (final int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int divisor = 1;
        for (int i = 1; i <= min; i++) {
            if (min % i == 0 && max % i == 0) {
                divisor = i;
            }
        }
        return divisor;
    }

    /*
        Given a positive integer n, return the smallest positive integer that is a multiple of both 2 and n.
     */
    public int smallestEvenMultiple(final int n) {
        return n % 2 == 0 ? n : n * 2;
    }

    /*
        Given two positive integers a and b, return the number of common factors of a and b.
        An integer x is a common factor of a and b if x divides both a and b.
     */
    public int commonFactors(final int a, final int b) {
        int factors = 0;
        final int min = Math.min(a, b);
        for (int i = 1; i <= min; i++) {
            if (a % i == 0 && b % i == 0) {
                factors++;
            }
        }
        return factors;
    }
}
