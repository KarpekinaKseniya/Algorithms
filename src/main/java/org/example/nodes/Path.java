package org.example.nodes;

import org.example.nodes.models.TreeNode;

public class Path {

    /*
        Given the root of a binary tree and an integer targetSum,
        return true if the tree has a root-to-leaf path such that adding up all
        the values along the path equals targetSum.

        A leaf is a node with no children.
    */
    public boolean hasPathSum(final TreeNode root, final int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.getLeft() == null && root.getRight() == null && targetSum == root.getVal()) {
            return true;
        }

        return hasPathSum(root.getLeft(), targetSum - root.getVal()) ||
                hasPathSum(root.getRight(), targetSum - root.getVal());
    }

    /*
        You are given the root of a binary tree.

        A ZigZag path for a binary tree is defined as follow:
            Choose any node in the binary tree and a direction (right or left).
            If the current direction is right, move to the right child of the current node;
                otherwise, move to the left child.
            Change the direction from right to left or from left to right.
            Repeat the second and third steps until you can't move in the tree.
            Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

        Return the longest ZigZag path contained in that tree.
    */
    public int longestZigZag(final TreeNode root) {
        int A = process(root.getLeft(), true, 0);
        int B = process(root.getRight(), false, 0);
        return Math.max(A, B);
    }

    private int process(final TreeNode root, final boolean direct, final int count) {
        if (root == null) return count;

        int A = 0, B = 0, C = 0, D = 0;
        if (direct) {
            A = process(root.getRight(), false, count + 1);
            B = process(root.getLeft(), true, 0);
        } else {
            C = process(root.getLeft(), true, count + 1);
            D = process(root.getRight(), false, 0);
        }
        A = Math.max(A, B);
        C = Math.max(C, D);
        return Math.max(A, C);
    }
}
