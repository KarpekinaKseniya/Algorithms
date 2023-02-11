package org.example;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
    You are given an integer n, the number of nodes in a directed graph where the nodes
    are labeled from 0 to n - 1. Each edge is red or blue in this graph,
    and there could be self-edges and parallel edges.
    You are given two arrays redEdges and blueEdges where:
        redEdges[i] = [ai, bi] indicates that there is a directed red edge
        from node ai to node bi in the graph, and
        blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj
        to node vj in the graph.
        Return an array answer of length n, where each answer[x] is the length of the shortest path
        from node 0 to node x such that the edge colors alternate along the path, or -1 if
        such a path does not exist.
*/
public class AlternatingColors {

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        final Set<Integer>[][] graph = new HashSet[2][n];
        for (int i = 0; i < n; i++) {
            graph[0][i] = new HashSet<>();
            graph[1][i] = new HashSet<>();
        }
        for (final int[] re : redEdges) {
            graph[0][re[0]].add(re[1]);
        }
        for (final int[] blu : blueEdges) {
            graph[1][blu[0]].add(blu[1]);
        }
        int[][] res = new int[2][n];
        for (int i = 1; i < n; i++) {
            res[0][i] = 2 * n;
            res[1][i] = 2 * n;
        }
        final Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        q.offer(new int[]{0, 1});
        while (!q.isEmpty()) {
            final int[] cur = q.poll();
            final int vert = cur[0];
            final int color = cur[1];
            for (final int next : graph[1 - color][vert]) {
                if (res[1 - color][next] == 2 * n) {
                    res[1 - color][next] = 1 + res[color][vert];
                    q.offer(new int[]{next, 1 - color});
                }
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            final int t = Math.min(res[0][i], res[1][i]);
            ans[i] = (t == 2 * n) ? -1 : t;
        }
        return ans;
    }
}
