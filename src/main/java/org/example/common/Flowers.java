package org.example.common;

import java.util.Arrays;

public class Flowers {

    /*
      You have a long flowerbed in which some plots are planted, and some are not.
      However, flowers cannot be planted in adjacent plots.
      Given an integer array flowerbed containing 0's and 1's,
        where 0 means empty and 1 means not empty, and an integer n,
        return if n new flowers can be planted in the flowerbed
        without violating the no-adjacent-flowers rule.
    */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        final int len = flowerbed.length;
        for (int i = 0; i < len; i += 2) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == len - 1
                    || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                n--;
            }
            if (n <= 0) {
                return true;
            }
        }
        return false;
    }

    /*
        You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi]
        means the ith flower will be in full bloom from starti to endi (inclusive). You are also given
        a 0-indexed integer array people of size n, where people[i] is the time that the ith person will
        arrive to see the flowers.
        Return an integer array answer of size n, where answer[i] is the number of flowers that are in
        full bloom when the ith person arrives.
    */
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int m = flowers.length;
        int[] starts = new int[m];
        int[] ends = new int[m];
        for (int i = 0; i < m; i++) {
            starts[i] = flowers[i][0];
            ends[i] = flowers[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int n = people.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int open, close;
            if (people[i] < starts[0]) {
                open = 0;
            } else if (people[i] >= starts[m - 1]) {
                open = m;
            } else {
                int l = 0, r = m - 1;
                while (l < r) {
                    int mid = (l + r + 1) >> 1;
                    if (starts[mid] <= people[i]) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                open = l + 1;
            }
            if (people[i] <= ends[0])
                close = 0;
            else if (people[i] > ends[m - 1]) {
                close = m;
            } else {
                int l = 0, r = m - 1;
                while (l < r) {
                    int mid = (l + r + 1) >> 1;
                    if (ends[mid] < people[i]) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                close = l + 1;
            }
            result[i] = open - close;
        }
        return result;
    }
}
