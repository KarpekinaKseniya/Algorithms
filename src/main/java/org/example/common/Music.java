package org.example.common;

public class Music {

    /*
        Your music player contains n different songs.
        You want to listen to goal songs (not necessarily different) during your trip.
        To avoid boredom, you will create a playlist so that:
            Every song is played at least once.
            A song can only be played again only if k other songs have been played.
        Given n, goal, and k, return the number of possible playlists that you can create.
        Since the answer can be very large, return it modulo 10^9 + 7.
    */
    public int numMusicPlaylists(int n, int goal, int k) {
        final int MOD = (int) 1e9 + 7;
        long[][] dp = new long[2][n + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= goal; i++) {
            dp[i % 2][0] = 0;
            for (int j = 1; j <= Math.min(i, n); j++) {
                dp[i % 2][j] = dp[(i - 1) % 2][j - 1] * (n - (j - 1)) % MOD;
                if (j > k)
                    dp[i % 2][j] = (dp[i % 2][j] + dp[(i - 1) % 2][j] * (j - k)) % MOD;
            }
        }

        return (int) dp[goal % 2][n];
    }

}
