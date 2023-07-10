package org.example.nodes;

import org.example.nodes.models.TreeNode;

public class Depth {

    /*
        Given a binary tree, find its minimum depth.
        The minimum depth is the number of nodes along the shortest path from
        the root node down to the nearest leaf node.
        Note: A leaf is a node with no children.
    */
    public int minDepth(final TreeNode root) {
        if (root == null) return 0;
        final int left = minDepth(root.getLeft());
        final int right = minDepth(root.getRight());
        return (left == 0 || right == 0)
                ? left + right + 1
                : Math.min(left, right) + 1;
    }

}
