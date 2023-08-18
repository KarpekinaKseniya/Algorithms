package org.example.url;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class NetworkRank {
    /*
        There is an infrastructure of n cities with some number of roads connecting these cities.
        Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.
        The network rank of two different cities is defined as the total number of directly connected
        roads to either city. If a road is directly connected to both cities, it is only counted once.
        The maximal network rank of the infrastructure is the maximum network rank of all pairs of
        different cities.
        Given the integer n and the array roads, return the maximal network rank of the entire
        infrastructure.
    */
    public int maximalNetworkRank(int n, int[][] roads) {
        Map<Integer, HashSet<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjList.put(i, new HashSet<>());
        }
        for (final int[] road : roads) {
            adjList.get(road[0]).add(road[1]);
            adjList.get(road[1]).add(road[0]);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int tmp = 0;
                if (adjList.get(i).contains(j)) tmp--;
                tmp += adjList.get(i).size() + adjList.get(j).size();
                max = Math.max(tmp, max);
            }
        }
        return max;
    }


}
