package org.example.common;

/*
    Given a 2D grid consists of 0s (land) and 1s (water).
    An island is a maximal 4-directionally connected group of 0s and a closed island is
    an island totally (all left, top, right, bottom) surrounded by 1s.
    Return the number of closed islands.
*/
public class Islands {

    public int closedIsland(final int[][] grid) {
        int count = 0;
        final int n = grid.length;
        final int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    count += dfs(i, j, grid, n, m);
                }
            }
        }
        return count;
    }

    private static int dfs(final int i, final int j, final int[][] a, final int n, final int m) {
        if (i >= n || j >= m || i < 0 || j < 0) return 0;
        if (a[i][j] == 1) return 1;
        a[i][j] = 1;
        int up = dfs(i - 1, j, a, n, m);
        int down = dfs(i + 1, j, a, n, m);
        int left = dfs(i, j - 1, a, n, m);
        int right = dfs(i, j + 1, a, n, m);
        return up & down & left & right;
    }
}
