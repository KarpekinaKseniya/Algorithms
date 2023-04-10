package org.example.nodes;

import org.example.nodes.models.TreeNode;

/*
    Given the root of a binary tree and an integer targetSum,
    return true if the tree has a root-to-leaf path such that adding up all
    the values along the path equals targetSum.

    A leaf is a node with no children.
*/
public class Path {

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
}
