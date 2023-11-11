package org.example.another;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
    There is a directed weighted graph that consists of n nodes numbered from 0 to n - 1.
    The edges of the graph are initially represented by the given array edges where
    edges[i] = [from-i, to-i, edgeCost-i] meaning that there is an edge from from-i to to-i with the cost
    edgeCost-i.
    Implement the Graph class:
        Graph(int n, int[][] edges) initializes the object with n nodes and the given edges.
        addEdge(int[] edge) adds an edge to the list of edges where edge = [from, to, edgeCost].
        It is guaranteed that there is no edge between the two nodes before adding this one.
        int shortestPath(int node1, int node2) returns the minimum cost of a path from node1 to node2.
        If no path exists, return -1. The cost of a path is the sum of the costs of the edges in the path.
*/
public class Graph {

    private List<List<int[]>> res;
    private int n;

    public Graph(int n, int[][] edges) {
        this.n = n;
        this.res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            res.get(u).add(new int[]{v, w});
        }
    }

    public void addEdge(int[] edge) {
        int u = edge[0], v = edge[1], w = edge[2];
        res.get(u).add(new int[]{v, w});
    }

    public int shortestPath(int node1, int node2) {
        Queue<int[]> nm = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        nm.offer(new int[]{node1, 0});
        boolean[] visited = new boolean[n];
        while (!nm.isEmpty()) {
            int[] curr = nm.poll();
            int u = curr[0], d = curr[1];
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            if (u == node2) return d;
            for (int[] e : res.get(u)) {
                int v = e[0], w = e[1];
                if (!visited[v]) {
                    nm.offer(new int[]{v, d + w});
                }
            }
        }
        return -1;
    }

}
