package org.example.common;

/*
    Given the integers zero, one, low, and high, we can construct a string by starting with
    an empty string, and then at each step perform either of the following:
        Append the character '0' zero times.
        Append the character '1' one times.
        This can be performed any number of times.
    A good string is a string constructed by the above process having a length between low and
    high (inclusive).
    Return the number of different good strings that can be constructed satisfying these properties.
    Since the answer can be large, return it modulo 10^9 + 7.
*/
public class GoodStrings {

    private static final int MODULO = 100_000_000_7;

    public int countGoodStrings(final int low, final int high, final int zero, final int one) {
        int dp[] = new int[high + 1];
        dp[0] = 1;
        int ans = 0;
        for (int i = 1; i <= high; i++) {
            dp[i] = ((i - zero >= 0 ? dp[i - zero] : 0) + (i - one >= 0 ? dp[i - one] : 0)) % MODULO;
            if (i >= low) {
                ans = (ans + dp[i]) % MODULO;
            }
        }
        return ans;
    }
}
