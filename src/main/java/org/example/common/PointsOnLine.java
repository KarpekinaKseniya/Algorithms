package org.example.common;

public class PointsOnLine {

    /*
        Given an array of points where points[i] = [x-i, y-i] represents a point on the X-Y plane,
        return the maximum number of points that lie on the same straight line.
    */
    public int maxPoints(final int[][] points) {
        final int count = points.length;
        if (count <= 2) {
            return count;
        }
        int result = 2;
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                int temp = 2;
                for (int k = 0; k < count; k++) {
                    if (k != i && k != j) {
                        int x = (points[j][1] - points[i][1]) * (points[k][0] - points[i][0]);
                        int y = (points[k][1] - points[i][1]) * (points[j][0] - points[i][0]);
                        if (x == y) {
                            temp++;
                        }
                    }
                }
                if (temp > result) {
                    result = temp;
                }
            }
        }
        return result;
    }

    /*
        You are given an array points representing integer coordinates of some points on a 2D-plane,
        where points[i] = [xi, yi].
        The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between
        them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
        Return the minimum cost to make all points connected. All points are connected if there is
        exactly one simple path between any two points.
    */
    public int minCostConnectPoints(int[][] points) {
        int len = points.length;
        int[] disArr = new int[len];
        for (int i = 1; i < len; ++i) {
            disArr[i] = Integer.MAX_VALUE;
        }
        boolean[] visited = new boolean[len];
        visited[0] = true;
        int numEdge = 0;
        int cur = 0;
        int res = 0;
        while (numEdge++ < len - 1) {
            int minEdge = Integer.MAX_VALUE;
            int next = -1;
            for (int i = 0; i < len; ++i) {
                if (!visited[i]) {
                    int dis = Math.abs(points[cur][0] - points[i][0]) + Math.abs(points[cur][1] - points[i][1]);
                    disArr[i] = Math.min(dis, disArr[i]);
                    if (disArr[i] < minEdge) {
                        minEdge = disArr[i];
                        next = i;
                    }
                }
            }
            cur = next;
            visited[cur] = true;
            res += minEdge;
        }
        return res;
    }
}
