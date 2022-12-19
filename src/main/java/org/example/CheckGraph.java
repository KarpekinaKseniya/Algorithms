package org.example;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
    There is a bi-directional graph with n vertices, where each vertex is labeled
    from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges,
    where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi.
    Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

    You want to determine if there is a valid path that exists from vertex source to vertex destination.

    Given edges and the integers n, source, and destination, return true if
    there is a valid path from source to destination, or false otherwise.
*/
public class CheckGraph {

    public boolean validPath(final int n, final int[][] edges, final int source, final int destination) {
        final ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        final boolean[] visited = new boolean[n];
        final Queue<Integer> que = new PriorityQueue<>();
        que.add(source);
        while (que.size() > 0) {
            final int rem = que.remove();
            if (rem == destination)
                return true;
            for (int i = 0; i < list.get(rem).size(); i++) {
                if (!visited[list.get(rem).get(i)]) {
                    visited[list.get(rem).get(i)] = true;
                    que.add(list.get(rem).get(i));
                }
            }
        }
        return false;
    }
}
