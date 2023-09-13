package org.example.common;

import java.util.ArrayList;
import java.util.List;


public class Candies {

    /*
        There are n kids with candies. You are given an integer array candies, where each candies[i]
        represents the number of candies the i-th kid has, and an integer extraCandies, denoting the number
        of extra candies that you have.
        Return a boolean array result of length n, where result[i] is true if, after giving the i-th kid all
        the extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.
        Note that multiple kids can have the greatest number of candies.
    */
    public List<Boolean> kidsWithCandies(final int[] candies, final int extraCandies) {
        int max = Integer.MIN_VALUE;
        for (final int val : candies) {
            if (max < val) {
                max = val;
            }
        }
        final List<Boolean> result = new ArrayList<>();
        for (final int val : candies) {
            if ((val + extraCandies) >= max) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }

    /*
        There are n children standing in a line. Each child is assigned a rating value given
            in the integer array ratings.
        You are giving candies to these children subjected to the following requirements:
            Each child must have at least one candy.
            Children with a higher rating get more candies than their neighbors.
            Return the minimum number of candies you need to have to distribute the candies to the children.
    */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] LtoR = new int[n]; //Left to right
        int[] RtoL = new int[n]; //Right to left
        for (int i = 0; i < n; i++) {
            LtoR[i] = RtoL[i] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                LtoR[i] = LtoR[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                RtoL[i] = RtoL[i + 1] + 1;
            }
        }
        int[] ans = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            ans[i] = Math.max(LtoR[i], RtoL[i]);
            sum += ans[i];
        }
        return sum;
    }
}
