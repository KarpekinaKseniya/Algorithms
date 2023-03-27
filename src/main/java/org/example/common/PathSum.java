package org.example.common;

/*
    Given a m x n grid filled with non-negative numbers,
    find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
    Note: You can only move either down or right at any point in time.
 */
public class PathSum {

    public int minPathSum(final int[][] grid) {
        int cycle = grid.length - 1;
        while (cycle >= 0) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (cycle == grid.length - 1 && j == grid[0].length - 1) {
                    grid[cycle][j] = grid[cycle][j];
                } else if (cycle == grid.length - 1) {
                    grid[cycle][j] += grid[cycle][j + 1];
                } else if (j == grid[0].length - 1) {
                    grid[cycle][j] += grid[cycle + 1][j];
                } else {
                    grid[cycle][j] += Math.min(grid[cycle][j + 1], grid[cycle + 1][j]);
                }
            }
            cycle--;
        }

        return grid[0][0];
    }
}
