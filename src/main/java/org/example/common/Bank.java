package org.example.common;

public class Bank {

    /*
        Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.
        He starts by putting in $1 on Monday, the first day. Every day from Tuesday to Sunday,
        he will put in $1 more than the day before. On every subsequent Monday, he will put in $1
        more than the previous Monday.
        Given n, return the total amount of money he will have in the Leetcode bank at the end of
        the n-th day.
    */
    public int totalMoney(int n) {
        int money = 0;
        int price = 1;
        int newcash = 1;
        int days = 1;
        while (days <= n) {
            if (days % 7 == 0) {
                money += price;
                days++;
                newcash++;
                price = newcash;
            }
            if (days <= n) {
                money += price;
                price++;
                days++;
            }
        }
        return money;
    }

}
