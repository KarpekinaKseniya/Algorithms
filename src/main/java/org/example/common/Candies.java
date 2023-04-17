package org.example.common;

import java.util.ArrayList;
import java.util.List;

/*
    There are n kids with candies. You are given an integer array candies, where each candies[i]
    represents the number of candies the i-th kid has, and an integer extraCandies, denoting the number
    of extra candies that you have.
    Return a boolean array result of length n, where result[i] is true if, after giving the i-th kid all
    the extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.
    Note that multiple kids can have the greatest number of candies.
*/
public class Candies {

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
}
