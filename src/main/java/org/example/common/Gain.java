package org.example.common;

/*
    There is a biker going on a road trip.
    The road trip consists of n + 1 points at different altitudes.
    The biker starts his trip on point 0 with altitude equal 0.
    You are given an integer array gain of length n where gain[i] is the net gain in altitude
    between points i and i + 1 for all (0 <= i < n). Return the highest altitude of a point.
*/
public class Gain {

    public int largestAltitude(final int[] gain) {
        int altitude = 0, max = 0;
        for (final int g : gain) {
            altitude += g;
            max = Math.max(max, altitude);
        }
        return max;
    }

}
