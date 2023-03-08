package org.example.common;

import java.util.Arrays;

/*
    Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
    The guards have gone and will come back in h hours.
    Koko can decide her bananas-per-hour eating speed of k. Each hour,
    she chooses some pile of bananas and eats k bananas from that pile.
    If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas
    during this hour.
    Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
    Return the minimum integer k such that she can eat all the bananas within h hours.
*/
public class Bananes {

    public int minEatingSpeed(final int[] piles, final int h) {
        Arrays.sort(piles);
        int start = 1;
        int max = Integer.MIN_VALUE;
        for (final int pile : piles) {
            max = Math.max(max, pile);
        }
        int end = max;
        while (start < end) {
            final int mid = start + (end - start) / 2;
            if (isPossible(piles, mid, h)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public boolean isPossible(final int[] piles, final int value, final int h) {
        int hours = 0;
        for (final int pile : piles) {
            int div = pile / value;
            hours += div;
            if (pile % value != 0) hours++;
        }
        return hours <= h;
    }
}
