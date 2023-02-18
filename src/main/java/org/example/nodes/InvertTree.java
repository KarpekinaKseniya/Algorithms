package org.example.nodes;

import org.example.nodes.models.TreeNode;

import java.util.LinkedList;

// Given the root of a binary tree, invert the tree, and return its root.
public class InvertTree {

    public TreeNode invertTree(final TreeNode root) {
        final LinkedList<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.add(root);
        }
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            if (temp.getLeft() != null) {
                q.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                q.add(temp.getRight());
            }
            TreeNode curr = temp.getLeft();
            temp.setLeft(temp.getRight());
            temp.setRight(curr);
        }
        return root;
    }
}
