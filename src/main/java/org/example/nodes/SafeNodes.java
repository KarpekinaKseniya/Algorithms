package org.example.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    There is a directed graph of n nodes with each node labeled from 0 to n - 1.
    The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer
    array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].
    A node is a terminal node if there are no outgoing edges. A node is a safe node if every
    possible path starting from that node leads to a terminal node (or another safe node).
    Return an array containing all the safe nodes of the graph. The answer should be sorted
    in ascending order.
*/
public class SafeNodes {

    public List<Integer> eventualSafeNodes(final int[][] graph) {
        final int len = graph.length;
        List<List<Integer>> revadj = new ArrayList<>();
        for (int i = 0; i < len; i++) revadj.add(new ArrayList<>());
        int[] indegree = new int[len];
        for (int i = 0; i < len; i++) {
            for (int it : graph[i]) {
                revadj.get(it).add(i);
                indegree[i]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if (indegree[i] == 0) q.add(i);
        }
        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.peek();
            q.remove();
            result.add(node);
            for (final int it : revadj.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0) q.add(it);
            }
        }
        Collections.sort(result);
        return result;
    }

}
