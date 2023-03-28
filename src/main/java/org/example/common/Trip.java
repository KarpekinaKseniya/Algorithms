package org.example.common;

public class Trip {

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

    /*
        You have planned some train traveling one year in advance.
        The days of the year in which you will travel are given as an integer array days.
        Each day is an integer from 1 to 365.

        Train tickets are sold in three different ways:
            a 1-day pass is sold for costs[0] dollars,
            a 7-day pass is sold for costs[1] dollars, and
            a 30-day pass is sold for costs[2] dollars.
        The passes allow that many days of consecutive travel.

        For example, if we get a 7-day pass on day 2, then we can travel
        for 7 days: 2, 3, 4, 5, 6, 7, and 8.
        Return the minimum number of dollars you need to travel every day in the given list of days.
     */
    public int minCostTickets(final int[] days, final int[] costs) {
        int[] dp = new int[days.length + 1];
        int monthStart = 1;
        int weekStart = 1;
        for (int i = 0; i < days.length; i++) {
            final int dayPass = costs[0] + dp[i];
            while (days[i] - days[weekStart - 1] + 1 > 7) {
                weekStart++;
            }
            final int weekPass = costs[1] + dp[weekStart - 1];
            while (days[i] - days[monthStart - 1] + 1 > 30) {
                monthStart++;
            }
            final int monthPass = costs[2] + dp[monthStart - 1];
            dp[i + 1] = Math.min(dayPass, Math.min(weekPass, monthPass));
        }
        return dp[days.length];
    }
}
