package org.example.common;

import java.util.Stack;

/*
    There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way
    to travel between two different cities (this network form a tree). Last year, The ministry of
    transport decided to orient the roads in one direction because they are too narrow.

    Roads are represented by connections where connections[i] = [a-i, b-i] represents a road from
    city a-i to city b-i.

    This year, there will be a big event in the capital (city 0), and many people want to travel to
    this city.

    Your task consists of reorienting some roads such that each city can visit the city 0.
    Return the minimum number of edges changed.

    It's guaranteed that each city can reach city 0 after reorder.
 */
public class City {

    public int minReorder(final int n, final int[][] connections) {
        boolean[] reach = new boolean[n];
        final int rows = connections.length;
        reach[0] = true;
        int count = 0;
        final Stack<Integer> s1 = new Stack<>();
        for (int i = 0; i < rows; i++) {
            if (reach[connections[i][0]]) {
                count++;
                reach[connections[i][1]] = true;
            } else if (reach[connections[i][1]]) {
                reach[connections[i][0]] = true;
            } else
                s1.push(i);
        }
        final Stack<Integer> s2 = new Stack<>();
        while (!s1.isEmpty() || !s2.isEmpty()) {
            count = getCount(connections, reach, count, s1, s2);
            count = getCount(connections, reach, count, s2, s1);
        }
        return count;
    }

    private int getCount(final int[][] connections, final boolean[] reach, int count, final Stack<Integer> s1, final Stack<Integer> s2) {
        while (!s1.isEmpty()) {
            int i = s1.pop();
            if (reach[connections[i][0]]) {
                count++;
                reach[connections[i][1]] = true;
            } else if (reach[connections[i][1]]) {
                reach[connections[i][0]] = true;
            } else
                s2.push(i);
        }
        return count;
    }
}
