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

    /*
        Given an integer num, return the number of digits in num that divide num.
        An integer val divides nums if nums % val == 0.
    */
    public int countDigits(final int num) {
        int count = 0;
        final int[] digits = String.valueOf(num).chars().map(Character::getNumericValue).toArray();
        for (final int digit : digits) {
            if (num % digit == 0) {
                count++;
            }
        }
        return count;
    }

    /*
        The Tribonacci sequence Tn is defined as follows:
        T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
        Given n, return the value of Tn.
     */
    public int tribonacci(final int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] fibonacci = new int[n + 1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        fibonacci[2] = 1;
        for (int i = 3; i <= n; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2] + fibonacci[i - 3];
        }
        return fibonacci[n];
    }

    //Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).
    public int countOdds(final int low, final int high) {
        if (high % 2 == 0 && low % 2 == 0) {
            return (high - low) / 2;
        }
        return (high - low) / 2 + 1;
    }
}
