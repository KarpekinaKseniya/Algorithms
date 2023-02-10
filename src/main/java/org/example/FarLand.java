package org.example;

import java.util.LinkedList;
import java.util.Queue;

/*
    Given an n x n grid containing only values 0 and 1,
    where 0 represents water and 1 represents land,
    find a water cell such that its distance to the nearest land cell is maximized,
    and return the distance. If no land or water exists in the grid, return -1.
    The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0)
    and (x1, y1) is |x0 - x1| + |y0 - y1|.
*/
public class FarLand {

    public int maxDistance(final int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        final Queue<int[]> q = new LinkedList<>();
        final int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    q.add(new int[]{i, j});
            }
        }
        if (q.size() == (m * n)) {
            return -1;
        }
        int level = 0;
        while (!q.isEmpty()) {
            final int size = q.size();
            level++;
            for (int i = 0; i < size; i++) {
                final int[] temp = q.remove();
                final int row = temp[0];
                final int col = temp[1];
                for (final int[] dir : directions) {
                    int cr = row + dir[0];
                    int cc = col + dir[1];
                    if (cr >= 0 && cr < m && cc >= 0 && cc < n && grid[cr][cc] == 0) {
                        grid[cr][cc] = 1;
                        q.add(new int[]{cr, cc});
                    }
                }
            }
        }
        return level - 1;
    }
}
