package org.example;

/*
    Given a positive integer n, find the pivot integer x such that:
        The sum of all elements between 1 and x inclusively equals the sum of all elements between x and n inclusively.
    Return the pivot integer x. If no such integer exists, return -1.
    It is guaranteed that there will be at most one pivot index for the given input.
*/
class PivotNumber {

    public int pivotInteger(final int n) {
        final int k = n * (n + 1) / 2;
        for (int i = 1; i <= n; i++) {
            if (i * i == k) {
                return i;
            }
        }
        return -1;
    }
}
