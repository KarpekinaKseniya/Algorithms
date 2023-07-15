package org.example.common;

import java.util.Arrays;

/*
    You are given an array of events where events[i] = [startDay-i, endDay-i, value-i].
    The ith event starts at startDay-i and ends at endDay-i, and if you attend this event, you
    will receive a value of value-i. You are also given an integer k which represents the maximum
    number of events you can attend.
    You can only attend one event at a time. If you choose to attend an event,
    you must attend the entire event. Note that the end day is inclusive: that is, you cannot
    attend two events where one of them starts and the other ends on the same day.
    Return the maximum sum of values that you can receive by attending events.
*/
public class Attended {

    public int maxValue(int[][] events, int k) {
        int max = 0;
        if (k == 1) {
            for (int[] event : events)
                max = Math.max(max, event[2]);

            return max;
        }
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int size = events.length;
        int[][] cache = new int[size + 1][k + 1];
        for (int i = size - 1; i >= 0; i--) {
            int next = binarySearch(events, events[i][1], i + 1, size);
            for (int j = 1; j <= k; j++) {
                cache[i][j] = Math.max(cache[i + 1][j],
                        cache[next][j - 1] + events[i][2]);
            }
        }
        return cache[0][k];
    }

    private int binarySearch(int[][] events, int targetEnd, int lo, int hi) {
        while (lo < hi) {
            int mid = (hi - lo) / 2 + lo;
            if (targetEnd >= events[mid][0]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

}
