package org.example.nodes;

import org.example.nodes.models.TreeNode;

/*
    Given the root of a Binary Search Tree (BST), return the minimum absolute difference between
    the values of any two different nodes in the tree.
*/
public class BinarySearchTree {
    private Integer prev;
    private int minDiff;

    public int getMinimumDifference(final TreeNode root) {
        prev = null;
        minDiff = Integer.MAX_VALUE;
        inorderTraversal(root);
        return minDiff;
    }

    private void inorderTraversal(final TreeNode node) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.getLeft());

        if (prev != null) {
            minDiff = Math.min(minDiff, node.getVal() - prev);
        }
        prev = node.getVal();

        inorderTraversal(node.getRight());
    }
}
