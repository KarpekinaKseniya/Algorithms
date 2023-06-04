package org.example.common;

/*
    There are n cities. Some of them are connected, while some are not.
    If city a is connected directly with city b, and city b is connected directly with city c,
    then city a is connected indirectly with city c.
    A province is a group of directly or indirectly connected cities and no other
    cities outside of the group.
    You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the i-th city and
    the j-th city are directly connected, and isConnected[i][j] = 0 otherwise.
*/
public class Province {

    public int findCircleNum(final int[][] isConnected) {
        int result = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[i][i] == 1) {
                result++;
                clear(isConnected, i);
            }
        }
        return result;
    }

    private static void clear(final int[][] isConnected, final int i) {
        int[] edges = isConnected[i];
        if (edges[i] == 1) {
            edges[i] = 0;
            for (int j = 0; j < edges.length; j++) {
                if (edges[j] == 1) {
                    edges[j] = 0;
                    clear(isConnected, j);
                }
            }
        }
    }

}
