package org.example.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    /*
    You are given a directed graph of n nodes numbered from 0 to n - 1,
    where each node has at most one outgoing edge.

    The graph is represented with a given 0-indexed array edges of size n,
    indicating that there is a directed edge from node i to node edges[i].
    If there is no outgoing edge from node i, then edges[i] == -1.

    Return the length of the longest cycle in the graph. If no cycle exists, return -1.

    A cycle is a path that starts and ends at the same node.

    Constraints:
        n == edges.length
        2 <= n <= 105
        -1 <= edges[i] < n
        edges[i] != i
    */
    public int longestCycle(final int[] edges) {
        boolean[] visited = new boolean[edges.length];
        int max = -1;
        for (int i = 0; i < edges.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int destination = edges[i];
                int length = 1;
                Map<Integer, Integer> nodeToDistance = new HashMap<>();
                nodeToDistance.put(i, 0);
                while (destination != -1 && !visited[destination]) {
                    nodeToDistance.put(destination, length);
                    visited[destination] = true;
                    destination = edges[destination];
                    length++;
                }
                if (destination != -1 && nodeToDistance.containsKey(destination)) {
                    max = Math.max(length - nodeToDistance.get(destination), max);
                }
            }
        }
        return max;
    }

    /*
        There is a directed graph of n colored nodes and m edges.
        The nodes are numbered from 0 to n - 1.

        You are given a string colors where colors[i] is a lowercase English letter representing
        the color of the ith node in this graph (0-indexed). You are also given a 2D array edges
        where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.

        A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that
        there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path
        is the number of nodes that are colored the most frequently occurring color along that path.

        Return the largest color value of any valid path in the given graph, or -1 if the graph
        contains a cycle.
     */
    public int largestPathValue(final String colors, final int[][] edges) {
        final List<List<Integer>> al = new ArrayList<>();
        final List<int[]> cnt = new ArrayList<>();
        final int n = colors.length();
        for (int i = 0; i < n; i++) {
            al.add(new ArrayList<>());
            cnt.add(new int[26]);
        }
        int[] indegrees = new int[n];
        for (int[] e : edges) {
            al.get(e[0]).add(e[1]);
            indegrees[e[1]]++;
        }
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0)
                q.add(i);
        }
        int res = 0, processed = 0;
        while (!q.isEmpty()) {
            final List<Integer> q1 = new ArrayList<>();
            for (final int i : q) {
                processed++;
                res = Math.max(res, ++cnt.get(i)[colors.charAt(i) - 'a']);
                for (final int j : al.get(i)) {
                    for (int k = 0; k < 26; k++)
                        cnt.get(j)[k] = Math.max(cnt.get(j)[k], cnt.get(i)[k]);
                    if (--indegrees[j] == 0)
                        q1.add(j);
                }
            }
            q = q1;
        }
        return processed != n ? -1 : res;
    }

    /*
        There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
        You are given a 2D array graph, where graph[u] is an array of nodes that node u
        is adjacent to. More formally, for each v in graph[u], there is an undirected edge
        between node u and node v. The graph has the following properties:
            There are no self-edges (graph[u] does not contain u).
            There are no parallel edges (graph[u] does not contain duplicate values).
            If v is in graph[u], then u is in graph[v] (the graph is undirected).
            The graph may not be connected, meaning there may be two nodes u and v such
                that there is no path between them.
        A graph is bipartite if the nodes can be partitioned into two independent sets A and B such
        that every edge in the graph connects a node in set A and a node in set B.
        Return true if and only if it is bipartite.
    */
    public boolean isBipartite(final int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);
        for (int i = 0; i < graph.length; i++)
            if (color[i] == -1 && !dfs(graph, i, color, 0)) return false;
        return true;
    }

    private boolean dfs(final int[][] graph, final int u, final int[] color, final int c) {
        color[u] = c;
        for (int v : graph[u])
            if (color[v] == -1 && !dfs(graph, v, color, (c + 1) % 2)) return false;
            else if (color[v] != (c + 1) % 2) return false;
        return true;
    }
}
