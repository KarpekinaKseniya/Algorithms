package org.example.nodes;

import org.example.nodes.models.TreeNode;

public class SameTrees {

    /*
    Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
     */
    public boolean isSymmetric(final TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.getLeft(), root.getRight());
    }

    /*
    Given the roots of two binary trees p and q, write a function to check if they are the same or not.
    Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
    */
    public boolean isSameTree(final TreeNode p, final TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.getVal() != q.getVal()) {
            return false;
        }
        return isSameTree(p.getLeft(), q.getLeft()) && isSameTree(p.getRight(), q.getRight());
    }

    private boolean isMirror(final TreeNode p, final TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.getVal() != q.getVal()) {
            return false;
        }
        return isMirror(p.getLeft(), q.getRight()) && isMirror(p.getRight(), q.getLeft());
    }
}
