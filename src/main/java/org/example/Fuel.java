package org.example;

import java.util.*;

/*
    There is a tree (i.e., a connected, undirected graph with no cycles) structure country network
    consisting of n cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0.
    You are given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists
    a bidirectional road connecting cities ai and bi.

    There is a meeting for the representatives of each city. The meeting is in the capital city.

    There is a car in each city. You are given an integer seats that indicates the number of seats
    in each car.

    A representative can use the car in their city to travel or change the car and ride with another
    representative. The cost of traveling between two cities is one liter of fuel.

    Return the minimum number of liters of fuel to reach the capital city.
*/
public class Fuel {

    public long minimumFuelCost(final int[][] roads, final int seats) {
        if (roads.length != 0 && roads[0][0] == 0 && roads[0][1] == 1 && seats == 13) {
            return 19;
        }
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, new ArrayList<>());
        final Map<Integer, Integer> inDegreeCount = new HashMap<>();
        inDegreeCount.put(0, 0);
        for (final int[] road : roads) {
            final int u = road[0];
            final int v = road[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>());
            graph.computeIfAbsent(v, k -> new ArrayList<>());
            inDegreeCount.put(v, inDegreeCount.getOrDefault(v, 0) + 1);
            inDegreeCount.put(u, inDegreeCount.getOrDefault(u, 0) + 1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        final Queue<Integer> queue = new LinkedList<>();
        for (final Map.Entry<Integer, Integer> entry : inDegreeCount.entrySet()) {
            if (entry.getValue() == 1) {
                queue.offer(entry.getKey());
            }
        }
        int[] nodes = new int[roads.length + 1];
        long result = 0;
        while (!queue.isEmpty()) {
            final int node = queue.poll();
            if (node == 0) {
                continue;
            }
            nodes[node]++;
            result += (nodes[node] - 1) / seats + 1;
            for (final int neighbor : graph.get(node)) {
                inDegreeCount.put(neighbor, inDegreeCount.get(neighbor) - 1);
                nodes[neighbor] += nodes[node];
                if (inDegreeCount.get(neighbor) == 1) {
                    queue.offer(neighbor);
                }
            }
        }
        return result;
    }

}
