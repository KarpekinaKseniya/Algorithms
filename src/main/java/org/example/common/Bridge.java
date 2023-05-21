package org.example.common;

import java.util.LinkedList;
import java.util.Queue;

/*
    You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
    An island is a 4-directionally connected group of 1's not connected to any other 1's.
    There are exactly two islands in grid.
    You may change 0's to 1's to connect the two islands to form one island.
    Return the smallest number of 0's you must flip to connect the two islands.
*/
public class Bridge {

    public int shortestBridge(final int[][] a) {
        boolean found = false;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 1 && !found) {
                    found = true;
                    dfs(a, i, j);
                }
                if (found && a[i][j] == 1) q.add(new int[]{i, j});
            }
        }
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int c = 0; c < size; c++) {
                int b[] = q.remove();
                int i = b[0];
                int j = b[1];
                if ((i > 0 && a[i - 1][j] == 2) || (i < a.length - 1 && a[i + 1][j] == 2) || (j > 0 && a[i][j - 1] == 2) ||
                        (j < a[0].length - 1 && a[i][j + 1] == 2)) return ans;
                if (i > 0 && a[i - 1][j] == 0) {
                    a[i - 1][j] = 1;
                    q.add(new int[]{i - 1, j});
                }
                if (i < a.length - 1 && a[i + 1][j] == 0) {
                    a[i + 1][j] = 1;
                    q.add(new int[]{i + 1, j});
                }
                if (j > 0 && a[i][j - 1] == 0) {
                    a[i][j - 1] = 1;
                    q.add(new int[]{i, j - 1});
                }
                if (j < a[0].length - 1 && a[i][j + 1] == 0) {
                    a[i][j + 1] = 1;
                    q.add(new int[]{i, j + 1});
                }
            }
            ans++;
        }
        return 0;
    }

    private void dfs(final int[][] a, final int i, final int j) {
        if (i < 0 || i >= a.length || j < 0 || j >= a[0].length || a[i][j] == 0 || a[i][j] == 2) return;
        a[i][j] = 2;
        dfs(a, i - 1, j);
        dfs(a, i + 1, j);
        dfs(a, i, j - 1);
        dfs(a, i, j + 1);
    }

}
