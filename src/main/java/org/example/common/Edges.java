package org.example.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and an array
    edges where edges[i] = [ai, bi, weighti] represents a bidirectional and weighted edge between
    nodes ai and bi. A minimum spanning tree (MST) is a subset of the graph's edges that connects
    all vertices without cycles and with the minimum possible total edge weight.
    Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST).
    An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical
    edge. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.
    Note that you can return the indices of the edges in any order.
*/
public class Edges {
    class UnionFind {
        int[] root;
        int count;

        public UnionFind(int size) {
            root = new int[size];
            count = size;
            for (int i = 0; i < size; i++) root[i] = i;
        }

        public int find(int x) {
            if (root[x] == x) return x;
            return root[x] = find(root[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX != rootY) {
                root[rootX] = rootY;
                count--;
            }
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] oldedges) {
        int[][] edges = new int[oldedges.length][4];

        for (int i = 0; i < edges.length; i++) {
            edges[i][0] = oldedges[i][0];
            edges[i][1] = oldedges[i][1];
            edges[i][2] = oldedges[i][2];
            edges[i][3] = i;
        }

        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        int min = cost(n, edges, null, null);
        List<Integer> criticals = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int costWithoutEdge = cost(n, edges, null, edge);
            if (costWithoutEdge != min) {
                criticals.add(edge[3]);
            } else if (costWithoutEdge == min) {
                int costWithEdge = cost(n, edges, edge, null);
                if (costWithEdge == min) {
                    pseudo.add(edge[3]);
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(criticals);
        ans.add(pseudo);
        return ans;
    }

    public int cost(int n, int[][] edges, int[] pick, int[] skip) {
        UnionFind uf = new UnionFind(n);

        int ans = 0;

        if (pick != null) {
            ans += pick[2];
            uf.union(pick[0], pick[1]);
        }

        for (int[] edge : edges) {
            if (skip != null) {
                if (edge[0] == skip[0] && edge[1] == skip[1]) continue;
            }

            if (uf.find(edge[0]) == uf.find(edge[1])) continue;
            uf.union(edge[0], edge[1]);
            ans += edge[2];
            if (uf.count == 1) break;
        }
        return ans;
    }
}
