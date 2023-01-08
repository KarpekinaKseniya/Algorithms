package org.example;

/*
    Given an array of points where points[i] = [x-i, y-i] represents a point on the X-Y plane,
    return the maximum number of points that lie on the same straight line.
*/
public class PointsOnLine {

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
}
