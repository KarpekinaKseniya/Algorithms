package org.example.common;

/*
    You are given an array points where points[i] = [x-i, y-i]
    is the coordinates of the ith point on a 2D plane.
    Multiple points can have the same coordinates.

    You are also given an array queries where queries[j] = [x-j, y-j, r-j]
    describes a circle centered at (x-j, y-j) with a radius of r-j.

    For each query queries[j], compute the number of points inside the jth circle.
    Points on the border of the circle are considered inside.

    Return an array answer, where answer[j] is the answer to the j-th query.
*/
public class Circles {

    public int[] countPoints(final int[][] points, final int[][] queries) {
        int[] ans = new int[queries.length];
        int i = 0;
        for (final int[] query : queries) {
            int x0 = query[0], y0 = query[1], r = query[2];
            for (final int[] point : points) {
                int x = point[0], y = point[1];
                int dx = x - x0, dy = y - y0;
                if (dx * dx + dy * dy <= r * r) {
                    ++ans[i];
                }
            }
            ++i;
        }
        return ans;
    }
}
