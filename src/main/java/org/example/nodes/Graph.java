package org.example.nodes;

import org.example.nodes.models.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
    Given a reference of a node in a connected undirected graph.
    Return a deep copy (clone) of the graph.
    Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

        class Node {
            public int val;
            public List<Node> neighbors;
        }

    Test case format:
        For simplicity, each node's value is the same as the node's index (1-indexed).
        For example, the first node with val == 1, the second node with val == 2, and so on.
        The graph is represented in the test case using an adjacency list.
        An adjacency list is a collection of unordered lists used to represent a finite graph.
        Each list describes the set of neighbors of a node in the graph.
        The given node will always be the first node with val = 1.
        You must return the copy of the given node as a reference to the cloned graph.
*/
public class Graph {

    public Node cloneGraph(final Node node) {
        if (node == null) return null;
        final Map<Node, Node> map = new HashMap<>();
        final Queue<Node> q = new LinkedList<>();

        q.add(node);
        map.put(node, new Node(node.getVal(), new ArrayList<>()));

        while (!q.isEmpty()) {
            Node n = q.peek();
            q.poll();

            for (final Node e : n.getNeighbors()) {
                if (!map.containsKey(e)) {
                    map.put(e, new Node(e.getVal(), new ArrayList<>()));
                    q.add(e);
                }
                map.get(n).getNeighbors().add(map.get(e));
            }
        }
        return map.get(node);
    }

}
