package org.example.common;

import java.util.Arrays;

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

    /*
        Given an integer array nums of length n and an integer target,
        find three integers in nums such that the sum is closest to target.
        Return the sum of the three integers.
        You may assume that each input would have exactly one solution.
    */
    public int threeSumClosest(final int[] nums, final int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum > target) {
                    k--;
                } else {
                    j++;
                }
                if (Math.abs(target - sum) < min) {
                    min = Math.abs(target - sum);
                    result = sum;
                }
            }
        }
        return result;
    }

    /*
        Given an integer array nums where every element appears three times except for one,
        which appears exactly once. Find the single element and return it.
        You must implement a solution with a linear runtime complexity and use only constant extra space.
    */
    public int singleNumber(final int[] nums) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (final int num : nums) {
                count += (num >> i) & 1;
            }
            count = count % 3;
            if (count != 0) {
                result |= count << i;
            }
        }
        return result;
    }

    /*
        Given an integer array nums, return the number of the longest increasing subsequences.
        Notice that the sequence has to be strictly increasing.
     */
    public int findNumberOfLIS(final int[] nums) {
        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        int result = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == maxLength) {
                result += count[i];
            }
        }
        return result;
    }

    //Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
    public double myPow(double x, int n) {
        double result = 1.0;
        while (n != 0) {
            if (Math.abs(n) % 2 == 0) {
                x *= x;
                n /= 2;
            }
            if (n > 0) {
                result *= x;
                n--;
            } else {
                result *= (1 / x);
                n++;
            }
        }
        return result;
    }
}
