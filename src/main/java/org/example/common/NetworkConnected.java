package org.example.common;

/*
    There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming
    a network where connections[i] = [a-i, b-i] represents a connection between computers a-i and b-i.
    Any computer can reach any other computer directly or indirectly through the network.

    You are given an initial computer network connections.
    You can extract certain cables between two directly connected computers, and place
    them between any pair of disconnected computers to make them directly connected.

    Return the minimum number of times you need to do this in order to make all the computers
    connected. If it is not possible, return -1.
 */
public class NetworkConnected {

    public int makeConnected(final int n, final int[][] connections) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int leftWire = 0;
        int component = 0;
        for (final int[] connection : connections) {
            final int p1 = findPar(connection[0], parent);
            final int p2 = findPar(connection[1], parent);
            if (p1 != p2) {
                parent[p1] = p2;
            } else {
                leftWire++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                component++;
            }
        }
        return (component - 1) <= leftWire ? component - 1 : -1;
    }

    private int findPar(final int node, final int[] parent) {
        if (node == parent[node]) {
            return node;
        }
        return parent[node] = findPar(parent[node], parent);
    }
}
