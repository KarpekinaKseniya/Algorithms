package org.example.common;

import java.util.ArrayList;

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
public class Intervals {
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
}
