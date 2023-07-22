package org.example.common;

/*
    On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make
    exactly k moves. The rows and columns are 0-indexed, so the top-left cell is (0, 0), and
    the bottom-right cell is (n - 1, n - 1).
    A chess knight has eight possible moves it can make, as illustrated below. Each move is two
    cells in a cardinal direction, then one cell in an orthogonal direction.
    Each time the knight is to move, it chooses one of eight possible moves uniformly at
    random (even if the piece would go off the chessboard) and moves there.
    The knight continues moving until it has made exactly k moves or has moved off the chessboard.
    Return the probability that the knight remains on the board after it has stopped moving.
*/
public class Knight {

    public double knightProbability(int n, int k, int row, int column) {
        if (k == 0) {
            return 1.0;
        }
        double[][] dp = new double[n][n];
        dp[row][column] = 1;
        int[][] val = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
        for (int ind = 1; ind <= k; ind++) {
            double[][] dp1 = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][j] != 0) {
                        for (int[] iterate : val) {
                            int ind_x = i + iterate[0];
                            int ind_y = j + iterate[1];
                            if (ind_x >= 0 && ind_y >= 0 && ind_x < n && ind_y < n) {
                                dp1[ind_x][ind_y] += dp[i][j] / 8.0;
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                System.arraycopy(dp1[i], 0, dp[i], 0, n);
            }
        }
        double ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += dp[i][j];
            }
        }
        return ans;
    }

}
