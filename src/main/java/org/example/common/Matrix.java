package org.example.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Matrix {

    /*
        You are given four integers row, cols, rCenter, and cCenter.
        There is a rows x cols matrix and you are on the cell with the coordinates (rCenter, cCenter).
        Return the coordinates of all cells in the matrix, sorted by their distance
        from (rCenter, cCenter) from the smallest distance to the largest distance.
        You may return the answer in any order that satisfies this condition.
        The distance between two cells (r1, c1) and (r2, c2) is |r1 - r2| + |c1 - c2|.
    */
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        int[][] res = new int[rows * cols][2];
        int idx = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[idx][0] = i;
                res[idx][1] = j;
                idx++;
            }
        }
        Arrays.sort(res, Comparator.comparingInt(o -> Math.abs(o[0] - rCenter) + Math.abs(o[1] - cCenter))
        );
        return res;
    }

    /*
        Given an m x n matrix, return all elements of the matrix in spiral order.
    */
    public List<Integer> spiralOrder(final int[][] matrix) {
        int m = matrix.length - 1, n = matrix[0].length;
        int x = 0, y = 0;
        final List<Integer> list = new ArrayList<>();

        while (true) {
            if (n == 0) break;
            for (var i = 0; i < n; i++, y++) {
                list.add(matrix[x][y]);
            }
            x++;
            y--;
            n--;
            if (m == 0) break;
            for (var i = 0; i < m; i++, x++) {
                list.add(matrix[x][y]);
            }
            y--;
            x--;
            m--;
            if (n == 0) break;
            for (var i = 0; i < n; i++, y--) {
                list.add(matrix[x][y]);
            }
            x--;
            y++;
            n--;
            if (m == 0) break;
            for (var i = 0; i < m; i++, x--) {
                list.add(matrix[x][y]);
            }
            y++;
            x++;
            m--;
        }
        return list;
    }

    /*
        Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
     */
    public int[][] generateMatrix(final int n) {
        int x = 1, cnt = 0;
        int[][] ans = new int[n][n];
        int[][] visited = new int[n][n];
        int i = 0, j = 0, k = 0;
        while (cnt < (n * n)) {
            if (visited[i][j] == 0) {
                ans[i][j] = x++;
                visited[i][j] = 1;
                if (i == k && j < n - 1 - k) j++;
                else if (j == n - 1 - k && i < n - 1 - k) i++;
                else if (i == n - 1 - k && j <= n - 1 - k && j != k) j--;
                else if (i <= n - 1 - k && j == k) i--;
                cnt++;
            } else {
                k++;
                i++;
                j++;
            }
        }
        return ans;
    }
}
