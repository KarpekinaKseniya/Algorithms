package org.example.common;

import java.util.Arrays;

/*
    There are n bulbs that are initially off. You first turn on all the bulbs,
    then you turn off every second bulb.
    On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
    For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.
    Return the number of bulbs that are on after n rounds.
*/
public class Bulb {

    public int bulbSwitch(final int n) {
        boolean[] bulbs = new boolean[n];
        Arrays.fill(bulbs, true);
        for (int i = 2; i <= n; i++) {
            for (int j = i - 1; j < n; j += i) {
                bulbs[j] = !bulbs[j];
            }
        }
        int count = 0;
        for (final boolean bulb : bulbs) {
            if (bulb) {
                count++;
            }
        }
        return count;
    }
}
