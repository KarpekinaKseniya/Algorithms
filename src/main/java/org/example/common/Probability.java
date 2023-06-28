package org.example.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
    You are given an undirected weighted graph of n nodes (0-indexed),
    represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a
    and b with a probability of success of traversing that edge succProb[i].
    Given two nodes start and end, find the path with the maximum probability of success to go
    from start to end and return its success probability.
    If there is no path from start to end, return 0. Your answer will be accepted if it differs
    from the correct answer by at most 1e-5.
*/
public class Probability {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            double p = succProb[i];
            graph.computeIfAbsent(a, x -> new ArrayList<>()).add(new int[]{b, i});
            graph.computeIfAbsent(b, x -> new ArrayList<>()).add(new int[]{a, i});
        }

        double[] probabilities = new double[n];
        probabilities[start] = 1.0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int[] neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                int next = neighbor[0];
                int i = neighbor[1];
                double newProb = probabilities[current] * succProb[i];

                if (newProb > probabilities[next]) {
                    probabilities[next] = newProb;
                    queue.offer(next);
                }
            }
        }

        return probabilities[end];
    }

}
