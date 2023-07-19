package org.example.common;

import java.util.ArrayList;
import java.util.Arrays;

public class Intervals {
    /*
        You are given an array of non-overlapping intervals where intervals[i] = [start-i, end-i]
        represent the start and the end of the ith interval and intervals is sorted in ascending order by
        start-i.
        You are also given an interval newInterval = [start, end] that represents the start
        and end of another interval.
        Insert newInterval into intervals such that intervals is still sorted in ascending order by
        start-i and intervals still does not have any overlapping intervals (merge overlapping
        intervals if necessary).
        Return intervals after the insertion.
    */
    public int[][] insert(final int[][] intervals, final int[] newInterval) {
        final ArrayList<int[]> list = new ArrayList<>();
        int[] newPeriod = newInterval;
        for (int[] interval : intervals) {
            if (interval[1] < newPeriod[0]) {
                list.add(interval);
            } else if (interval[0] > newPeriod[1]) {
                list.add(newPeriod);
                newPeriod = interval;
            } else {
                newPeriod[0] = Math.min(interval[0], newPeriod[0]);
                newPeriod[1] = Math.max(interval[1], newPeriod[1]);
            }
        }
        list.add(newPeriod);
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /*
        Given an array of intervals where intervals[i] = [start-i, end-i], return the minimum
        number of intervals you need to remove to make the rest of the intervals non-overlapping.
    */
    public int eraseOverlapIntervals(final int[][] intervals) {
        final int len = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int prev = 0;
        int count = 1;
        for (int i = 1; i < len; i++) {
            if (intervals[i][0] >= intervals[prev][1]) {
                prev = i;
                count++;
            }
        }
        return len - count;
    }

}
