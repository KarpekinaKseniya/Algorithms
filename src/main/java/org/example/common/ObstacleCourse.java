package org.example.common;

/*
    You want to build some obstacle courses.
    You are given a 0-indexed integer array obstacles of length n, where obstacles[i] describes
    the height of the ith obstacle.
    For every index i between 0 and n - 1 (inclusive), find the length of the longest obstacle course
    in obstacles such that:
        You choose any number of obstacles between 0 and i inclusive.
        You must include the ith obstacle in the course.
        You must put the chosen obstacles in the same order as they appear in obstacles.
        Every obstacle (except the first) is taller than or the same height as the obstacle
            immediately before it.
    Return an array ans of length n, where ans[i] is the length of the longest obstacle course
    for index i as described above.
*/
public class ObstacleCourse {

    public int[] longestObstacleCourseAtEachPosition(final int[] obstacles) {
        final int n = obstacles.length;
        int[] largestObstacleLength = new int[n];
        int[] minHeightStack = new int[n];
        int j = -1;
        for (int i = 0; i < n; i++) {
            if (j == -1 || (obstacles[i] >= minHeightStack[j])) {
                minHeightStack[++j] = obstacles[i];
                largestObstacleLength[i] = j + 1;
            } else {
                int left = 0, right = j;
                while (left < right) {
                    final int mid = left + (right - left) / 2;
                    if (minHeightStack[mid] <= obstacles[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                minHeightStack[right] = obstacles[i];
                largestObstacleLength[i] = right + 1;
            }
        }
        return largestObstacleLength;
    }

}
