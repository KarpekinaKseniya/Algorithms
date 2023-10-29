package org.example.common;

public class Product {

    /*
        There is a function signFunc(x) that returns:
            1 if x is positive.
            -1 if x is negative.
            0 if x is equal to 0.
        You are given an integer array nums. Let product be the product of all values in the array nums.
        Return signFunc(product).
    */
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

    /*
        There are buckets of liquid, where exactly one of the buckets is poisonous.
        To figure out which one is poisonous, you feed some number of (poor) pigs the liquid to see whether
        they will die or not. Unfortunately, you only have minutesToTest minutes to determine which bucket
        is poisonous.
        You can feed the pigs according to these steps:
            Choose some live pigs to feed.
            For each pig, choose which buckets to feed it. The pig will consume all the chosen buckets
            simultaneously and will take no time. Each pig can feed from any number of buckets, and each
            bucket can be fed from by any number of pigs.
            Wait for minutesToDie minutes. You may not feed any other pigs during this time.
            After minutesToDie minutes have passed, any pigs that have been fed the poisonous bucket will
            die, and all others will survive.
            Repeat this process until you run out of time.
        Given buckets, minutesToDie, and minutesToTest, return the minimum number of pigs needed to
        figure out which bucket is poisonous within the allotted time.
    */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        final int test = minutesToTest / minutesToDie;
        int i = 0;
        while (Math.pow(test + 1, i) < buckets) {
            i++;
        }
        return i;
    }
}
