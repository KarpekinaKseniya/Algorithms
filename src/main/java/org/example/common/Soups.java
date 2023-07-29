package org.example.common;

import java.util.Arrays;

/*
    There are two types of soup: type A and type B. Initially, we have n ml of each type of soup.
    There are four kinds of operations:
        Serve 100 ml of soup A and 0 ml of soup B,
        Serve 75 ml of soup A and 25 ml of soup B,
        Serve 50 ml of soup A and 50 ml of soup B, and
        Serve 25 ml of soup A and 75 ml of soup B.
    When we serve some soup, we give it to someone, and we no longer have it.
    Each turn, we will choose from the four operations with an equal probability 0.25.
    If the remaining volume of soup is not enough to complete the operation,
    we will serve as much as possible. We stop once we no longer have some quantity
    of both types of soup.
    Note that we do not have an operation where all 100 ml's of soup B are used first.
    Return the probability that soup A will be empty first, plus half the probability
    that A and B become empty at the same time. Answers within 10-5 of the actual answer
    will be accepted.
*/
public class Soups {

    private double[][] dp;

    public double soupServings(int n) {
        if (n >= 5000) {
            return 1;
        }
        dp = new double[n + 1][n + 1];
        for (double[] d : dp) {
            Arrays.fill(d, -1);
        }
        return serve(n, n);
    }

    public double serve(int a, int b) {
        if (a <= 0 || b <= 0) {
            if (a <= 0 && b <= 0) {
                return 0.5;
            } else if (a <= 0) {
                return 1.0;
            } else {
                return 0;
            }
        }
        if (dp[a][b] != -1) {
            return dp[a][b];
        }
        double s1 = serve(a - 100, b - 0);
        double s2 = serve(a - 75, b - 25);
        double s3 = serve(a - 50, b - 50);
        double s4 = serve(a - 25, b - 75);
        return dp[a][b] = (s1 + s2 + s3 + s4) / 4;
    }

}
