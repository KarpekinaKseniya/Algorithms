package org.example.common;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Grid {

    private final static int MOD = (int) (1e9 + 7);

    /*
        Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
        If there is no clear path, return -1.
        A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to
        the bottom-right cell (i.e., (n - 1, n - 1)) such that:
            All the visited cells of the path are 0.
            All the adjacent cells of the path are 8-directionally
            connected (i.e., they are different and they share an edge or a corner).
        The length of a clear path is the number of visited cells of this path.
    */
    public int shortestPathBinaryMatrix(final int[][] grid) {
        if (grid[0][0] == 1) return -1;

        var moves = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        var n = grid.length;
        var seen = new boolean[n][n];
        var queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{0, 0});

        for (var cnt = 1; !queue.isEmpty(); cnt++) {
            for (var i = queue.size(); i > 0; i--) {
                var cell = queue.poll();

                if (cell[0] == n - 1 && cell[1] == n - 1)
                    return cnt;

                for (var move : moves) {
                    var x = cell[0] + move[0];
                    var y = cell[1] + move[1];

                    if (x >= 0 && x < n && y >= 0 && y < n && !seen[x][y] && grid[x][y] == 0) {
                        seen[x][y] = true;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
        return -1;
    }

    /*
        Given a m x n matrix grid which is sorted in non-increasing order both row-wise and
        column-wise, return the number of negative numbers in grid.
    */
    public int countNegatives(final int[][] grid) {
        int count = 0;
        for (final int[] ints : grid) {
            for (final int anInt : ints) {
                if (anInt < 0)
                    count++;
            }
        }
        return count;
    }

    /*
        Given a 0-indexed n x n integer matrix grid,
        return the number of pairs (ri, cj) such that row ri and column cj are equal.
        A row and column pair is considered equal if they contain the same elements in the
        same order (i.e., an equal array).
     */
    public int equalPairs(final int[][] grid) {
        final int len = grid.length;
        Map<String, Integer> rows = new HashMap<>();
        Map<String, Integer> cols = new HashMap<>();
        for (int i = 0; i < len; i++) {
            StringBuilder r = new StringBuilder();
            StringBuilder c = new StringBuilder();
            for (int j = 0; j < len; j++) {
                r.append(grid[i][j]).append(' ');
                c.append(grid[j][i]).append(' ');
            }
            rows.put(r.toString(), rows.getOrDefault(r.toString(), 0) + 1);
            cols.put(c.toString(), cols.getOrDefault(c.toString(), 0) + 1);
        }
        int count = 0;
        for (final String s : rows.keySet()) {
            if (cols.containsKey(s)) {
                count += rows.get(s) * cols.get(s);
            }
        }
        return count;
    }

    /*
        You are given an m x n integer matrix grid, where you can move from a cell to
        any adjacent cell in all 4 directions.
        Return the number of strictly increasing paths in the grid such that you can
        start from any cell and end at any cell. Since the answer may be very large,
        return it modulo 10^9 + 7.
        Two paths are considered different if they do not have exactly the same sequence
        of visited cells.
    */
    public int countPaths(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        int paths = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                paths = (paths + solve(grid, i, j, -1, dp)) % MOD;
            }
        }
        return paths;

    }

    private int solve(int[][] grid, int i, int j, int prev, int[][] dp) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] <= prev) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int left = solve(grid, i, j - 1, grid[i][j], dp);
        int right = solve(grid, i, j + 1, grid[i][j], dp);
        int up = solve(grid, i - 1, j, grid[i][j], dp);
        int down = solve(grid, i + 1, j, grid[i][j], dp);

        return dp[i][j] = (1 + left + right + up + down) % MOD;
    }
}
