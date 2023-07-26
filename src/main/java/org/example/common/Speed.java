package org.example.common;

/*
    You are given a floating-point number hour,
    representing the amount of time you have to reach the office.
    To commute to the office, you must take n trains in sequential order.
    You are also given an integer array dist of length n, where dist[i] describes
    the distance (in kilometers) of the ith train ride.

    Each train can only depart at an integer hour, so you may need to wait in between each train ride.

    For example, if the 1st train ride takes 1.5 hours, you must wait for an additional 0.5 hours
    before you can depart on the 2nd train ride at the 2 hours mark.
    Return the minimum positive integer speed (in kilometers per hour) that all the trains must
    travel at for you to reach the office on time, or -1 if it is impossible to be on time.

    Tests are generated such that the answer will not exceed 10^7 and hour will have at most two
    digits after the decimal point.
*/
public class Speed {

    private static final int MAX_VALUE = 1_000_000_000;

    public int minSpeedOnTime(int[] dist, double hour) {
        final int n = dist.length;
        int left = 1, right = MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            double totalTime = 0;

            for (int i = 0; i < n - 1; i++) {
                totalTime += Math.ceil((double) dist[i] / mid);
            }

            totalTime += (double) dist[n - 1] / mid;
            if (totalTime <= hour) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left <= MAX_VALUE ? left : -1;
    }

}
