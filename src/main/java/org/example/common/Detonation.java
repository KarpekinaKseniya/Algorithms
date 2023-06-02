package org.example.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
    You are given a list of bombs.
    The range of a bomb is defined as the area where its effect can be felt.
    This area is in the shape of a circle with the center as the location of the bomb.
    The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [x-i, y-i, r-i].
    x-i and y-i denote the X-coordinate and Y-coordinate of the location of the ith bomb,
    whereas r-i denotes the radius of its range.
    You may choose to detonate a single bomb. When a bomb is detonated,
    it will detonate all bombs that lie in its range.
    These bombs will further detonate the bombs that lie in their ranges.
    Given the list of bombs, return the maximum number of bombs that can be detonated
    if you are allowed to detonate only one bomb.
*/
public class Detonation {

    public int maximumDetonation(final int[][] bombs) {
        final Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 0; i < bombs.length; i++) {
            graph.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < bombs.length - 1; i++) {
            for (int j = 1; j < bombs.length; j++) {
                final double distance = calculateDistance(bombs[i][0], bombs[i][1], bombs[j][0], bombs[j][1]);
                if (distance <= bombs[i][2])
                    graph.get(i).add(j);
                if (distance <= bombs[j][2])
                    graph.get(j).add(i);
            }
        }
        final Set<Integer> detonated = new HashSet<>();
        int maxDetonationsFromSingleSource = 0;
        for (int i = 0; i < bombs.length; i++) {
            detonated.clear();
            dfs(i, detonated, graph);
            maxDetonationsFromSingleSource = Math.max(maxDetonationsFromSingleSource, detonated.size());
            if (maxDetonationsFromSingleSource == graph.size())
                return maxDetonationsFromSingleSource;
        }

        return maxDetonationsFromSingleSource;
    }

    private double calculateDistance(final int x1, final int y1, final int x2, final int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private void dfs(final int node,
                     final Set<Integer> detonated,
                     final Map<Integer, ArrayList<Integer>> graph) {
        detonated.add(node);
        for (final int nextNode : graph.get(node)) {
            if (!detonated.contains(nextNode))
                dfs(nextNode, detonated, graph);
        }
    }

}
