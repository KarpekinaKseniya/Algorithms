package org.example.nodes;

import org.example.nodes.models.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
    Given an integer n, return a list of all possible full binary trees with n nodes.
    Each node of each tree in the answer must have Node.val == 0.
    Each element of the answer is the root node of one possible tree. You may return
    the final list of trees in any order.
    A full binary tree is a binary tree where each node has exactly 0 or 2 children.
*/
public class BinaryTree {

    public List<TreeNode> allPossibleFBT(final int n) {
        final List<TreeNode> result = new ArrayList<>();
        if (n == 1) {
            result.add(new TreeNode(0, null, null));
            return result;
        }
        for (int i = 1; i < n; i += 2) {
            final List<TreeNode> lefts = allPossibleFBT(i);
            final List<TreeNode> rights = allPossibleFBT(n - i - 1);
            for (final TreeNode left : lefts) {
                for (final TreeNode right : rights) {
                    TreeNode root = new TreeNode(0);
                    root.setLeft(left);
                    root.setRight(right);
                    result.add(root);
                }
            }
        }
        return result;
    }

}
