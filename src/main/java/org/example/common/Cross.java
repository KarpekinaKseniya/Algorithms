package org.example.common;

/*
    There is a 1-based binary matrix where 0 represents land and 1 represents water.
    You are given integers row and col representing the number of rows and columns in the matrix,
    respectively.
    Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded
    with water. You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that
    on the ith day, the cell on the rith row and cith column (1-based coordinates) will be covered
    with water (i.e., changed to 1).
    You want to find the last day that it is possible to walk from the top to the bottom
    by only walking on land cells. You can start from any cell in the top row and end at any cell in
    the bottom row. You can only travel in the four cardinal directions (left, right, up, and down).
    Return the last day where it is possible to walk from the top to the bottom by only walking
    on land cells.
*/
public class Cross {

    public int latestDayToCross(int row, int col, int[][] cells) {
        int i = 1;
        int j = row * col;
        while (i < j) {
            int mid = i + (j - i) / 2;
            boolean[][] grid = new boolean[row][col];
            boolean crosses = false;
            for (int k = 0; k < mid; k++) {
                grid[cells[k][0] - 1][cells[k][1] - 1] = true;
            }
            for (int k = 0; k < col; k++) {
                if (!grid[0][k] && dfs(0, k, grid)) {
                    crosses = true;
                    break;
                }
            }
            if (crosses) i = mid + 1;
            else j = mid;
        }
        return i - 1;
    }

    private boolean dfs(int i, int j, boolean[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j]) return false;
        if (i == grid.length - 1) return true;
        grid[i][j] = true;
        if (dfs(i + 1, j, grid) ||
                dfs(i - 1, j, grid) ||
                dfs(i, j + 1, grid) ||
                dfs(i, j - 1, grid)) return true;
        return false;
    }

}
