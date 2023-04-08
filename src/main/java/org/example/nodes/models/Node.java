package org.example.nodes.models;

import java.util.List;

public class Node {

    private int val;
    private List<Node> neighbors;

    public Node(final int val, final List<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }
}
