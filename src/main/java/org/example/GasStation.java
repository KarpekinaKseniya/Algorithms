package org.example;

/*
    There are n gas stations along a circular route, where the amount of gas at the i-th station is gas[i].

    You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from
    the i-th station to its next (i + 1)th station.
    You begin the journey with an empty tank at one of the gas stations.

    Given two integer arrays gas and cost, return the starting gas station's index if you can travel
    around the circuit once in the clockwise direction, otherwise return -1.
 */
public class GasStation {

    public int canCompleteCircuit(final int[] gas, final int[] cost) {
        int start = -1;
        int sum = 0;
        int asum = 0;
        for (int i = 0; i < gas.length; i++) {
            gas[i] -= cost[i];
            sum += gas[i];
            asum += gas[i];
            if (asum < 0) {
                asum = 0;
                start = i + 1;
            } else if (start == -1) {
                start = i;
            }
        }
        if (sum < 0) {
            return -1;
        }
        return start;
    }
}
