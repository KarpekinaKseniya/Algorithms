package org.example.common;

/*
    You are given three positive integers: n, index, and maxSum.
    You want to construct an array nums (0-indexed) that satisfies the following conditions:
        nums.length == n
        nums[i] is a positive integer where 0 <= i < n.
        abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
        The sum of all the elements of nums does not exceed maxSum.
        nums[index] is maximized.
    Return nums[index] of the constructed array.
    Note that abs(x) equals x if x >= 0, and -x otherwise.
*/
public class BoundedArray {

    public int maxValue(final int n, final int index, final int maxSum) {
        int sum = n;
        int l = index, r = index;
        int res = 1;
        while (sum + (r - l + 1) <= maxSum) {
            sum += r - l + 1;
            l = l == 0 ? 0 : l - 1;
            r = r == n - 1 ? r : r + 1;
            res++;
            if (l == 0 && r == n - 1) {
                int steps = 0;
                steps += (maxSum - sum) / n;
                sum += (steps * n);
                res += steps;
            }
        }
        return res;
    }

}
