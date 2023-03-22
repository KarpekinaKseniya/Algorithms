package org.example.nodes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
  You are given a positive integer n representing n cities numbered from 1 to n.
  You are also given a 2D array roads where roads[i] = [a-i, b-i, distance-i] indicates that there is
  a bidirectional road between cities a-i and b-i with a distance equal to distance-i.
  The cities graph is not necessarily connected.

  The score of a path between two cities is defined as the minimum distance of a road in this path.
  Return the minimum possible score of a path between cities 1 and n.
  Note:
    A path is a sequence of roads between two cities.
    It is allowed for a path to contain the same road multiple times,
      and you can visit cities 1 and n multiple times along the path.
  The test cases are generated such that there is at least one path between 1 and n.
 */
public class Cities {

  public int minScore(final int n, final int[][] roads) {
    int result = Integer.MAX_VALUE;
    final List<int[]>[] graph = new ArrayList[n + 1];
    for (int i = 0; i < graph.length; i++) {
      graph[i] = new ArrayList<>();
    }
    for (final int[] road : roads) {
      graph[road[0]].add(new int[]{road[1], road[2]});
      graph[road[1]].add(new int[]{road[0], road[2]});
    }
    final Set<Integer> seen = new HashSet<>();
    final Queue<Integer> queue = new ArrayDeque<>();
    queue.add(1);
    seen.add(1);
    while (!queue.isEmpty()) {
      final int node = queue.poll();
      for (final int[] neighbour : graph[node]) {
        result = Math.min(result, neighbour[1]);
        if (seen.add(neighbour[0])) {
          queue.add(neighbour[0]);
        }
      }
    }
    return result;
  }

}
