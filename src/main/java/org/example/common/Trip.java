package org.example.common;

/*
    You are given an array where time[i] denotes the time taken by
    the ith bus to complete one trip.
    Each bus can make multiple trips successively; that is, the next trip can start
    immediately after completing the current trip. Also, each bus operates independently;
    that is, the trips of one bus do not influence the trips of any other bus.
    You are also given an integer totalTrips, which denotes the number of trips all buses
    should make in total. Return the minimum time required for all buses to complete at least
    totalTrips trips.
*/
public class Trip {

    public long minimumTime(final int[] time, final int totalTrips) {
        long min = Integer.MAX_VALUE;
        for (final int unit : time) {
            min = Math.min(min, unit);
        }
        long max = min * totalTrips;
        long res = 0;
        while (min <= max) {
            long sum = 0;
            final long mid = min + (max - min) / 2;
            for (final int unit : time) {
                sum += (mid / unit);
            }
            if (sum == totalTrips) {
                res = mid;
                max = mid - 1;
            } else if (sum < totalTrips) {
                min = mid + 1;
                res = min;
            } else {
                max = mid - 1;
            }
        }
        return res;
    }
}
