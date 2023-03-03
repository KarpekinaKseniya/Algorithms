package org.example.common;

/*
    A conveyor belt has packages that must be shipped from one port to another within days.
    The ith package on the conveyor belt has a weight of weights[i].
    Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
    We may not load more weight than the maximum weight capacity of the ship.
    Return the least weight capacity of the ship that will result in all the packages
    on the conveyor belt being shipped within days.
*/
public class Ships {

    public int shipWithinDays(final int[] weights, final int days) {
        int sum = 0;
        int max = 0;
        int ans = 0;
        for (final int weight : weights) {
            sum += weight;
            max = Math.max(max, weight);
        }
        if (weights.length == days) {
            return max;
        }
        int l = max;
        int r = sum;
        while (l <= r) {
            final int mid = l + (r - l) / 2;
            if (findPackage(weights, mid, days)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }
    private boolean findPackage(final int[] weights, final int mid, final int days) {
        int d = 1;
        int sum = 0;
        for (final int weight : weights) {
            sum += weight;
            if (sum > mid) {
                d++;
                sum = weight;
            }
        }
        return d <= days;
    }
}
