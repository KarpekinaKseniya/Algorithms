package org.example.common;

import java.util.Arrays;

/*
    It is a sweltering summer day, and a boy wants to buy some ice cream bars.
    At the store, there are n ice cream bars. You are given an array costs of length n,
    where costs[i] is the price of the ith ice cream bar in coins.
    The boy initially has coins to spend, and he wants to buy as much ice cream bars as possible.
    Return the maximum number of ice cream bars the boy can buy with coins.
    Note: The boy can buy the ice cream bars in any order.
*/
public class IceCreams {

    public int maxIceCream(final int[] costs, final int coins) {
        Arrays.sort(costs);
        int count = 0;
        int sum = coins;
        for (final int cost : costs) {
            sum -= cost;
            if (sum >= 0) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

}
