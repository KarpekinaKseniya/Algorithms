package org.example.common;

public class Profit {

    /*
        You are given an array prices where prices[i] is the price of a given stock on the ith day,
        and an integer fee representing a transaction fee.
        Find the maximum profit you can achieve. You may complete as many transactions as you like,
        but you need to pay the transaction fee for each transaction.
        Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the
        stock before you buy again).
    */
    public int maxProfit(final int[] prices, final int fee) {
        int len = prices.length, buying = 0, selling = -prices[0];
        for (int i = 1; i < len; i++) {
            buying = Math.max(buying, selling + prices[i] - fee);
            selling = Math.max(selling, buying - prices[i]);
        }
        return buying;
    }

}
