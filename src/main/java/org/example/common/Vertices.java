package org.example.common;

import java.util.ArrayList;
import java.util.List;

/*
    Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array
    edges where edges[i] = [from-i, to-i] represents a directed edge from node from-i to node to-i.

    Find the smallest set of vertices from which all nodes in the graph are reachable.
    It's guaranteed that a unique solution exists.

    Notice that you can return the vertices in any order.
*/
public class Vertices {

    public List<Integer> findSmallestSetOfVertices(final int n, final List<List<Integer>> edges) {
        int[] indegree = new int[n];
        for (final List<Integer> list : edges) {
            indegree[list.get(1)]++;
        }
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                list.add(i);
            }
        }
        return list;
    }
}
