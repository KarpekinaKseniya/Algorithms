package org.example.common;

public class Lines {

    /*
        You are given two integer arrays nums1 and nums2. We write the integers of nums1 and nums2
        (in the order they are given) on two separate horizontal lines.
        We may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:
            nums1[i] == nums2[j], and
            the line we draw does not intersect any other connecting (non-horizontal) line.
        Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only
        belong to one connecting line).
        Return the maximum number of connecting lines we can draw in this way.
    */
    public int maxUncrossedLines(final int[] nums1, final int[] nums2) {
        final int m = nums1.length;
        final int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /*
        You are given an array coordinates, coordinates[i] = [x, y], where [x, y]
        represents the coordinate of a point. Check if these points make a straight line in the XY plane.
     */
    public boolean checkStraightLine(final int[][] coordinates) {
        final double slope = coordinates[1][0] == coordinates[0][0]
                ? Double.MAX_VALUE
                : (double) (coordinates[1][1] - coordinates[0][1]) / (coordinates[1][0] - coordinates[0][0]);
        final double c = coordinates[0][1] - slope * coordinates[0][0];
        for (var i = 2; i < coordinates.length; i++) {
            if (slope == Double.MAX_VALUE
                    ? coordinates[i][0] != coordinates[0][0]
                    : coordinates[i][1] - slope * coordinates[i][0] != c) {
                return false;
            }
        }

        return true;
    }
}
