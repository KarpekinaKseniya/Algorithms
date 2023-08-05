package org.example.nodes;

import org.example.nodes.models.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private Integer prev;
    private int minDiff;

    /*
       Given the root of a Binary Search Tree (BST), return the minimum absolute difference between
        the values of any two different nodes in the tree.
    */
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

    /*
        Given an integer n, return all the structurally unique BST's (binary search trees),
        which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
    */
    public List<TreeNode> generateTrees(int n) {
        List<List<List<TreeNode>>> dp = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            dp.add(new ArrayList<>());
            for (int j = 0; j <= n; ++j) {
                dp.get(i).add(new ArrayList<>());
            }
        }
        return getTrees(1, n, dp);
    }

    private List<TreeNode> getTrees(int start, int end, List<List<List<TreeNode>>> dp) {
        if (start > end) {
            List<TreeNode> o = new ArrayList<>();
            o.add(null);
            return o;
        }
        if (dp.get(start).get(end).size() != 0) {
            return dp.get(start).get(end);
        }
        List<TreeNode> output = new ArrayList<>();
        for (int i = start; i <= end; ++i) {
            List<TreeNode> left = getTrees(start, i - 1, dp);
            List<TreeNode> right = getTrees(i + 1, end, dp);
            for (final TreeNode node : left) {
                for (final TreeNode treeNode : right) {
                    TreeNode root = new TreeNode(i);
                    root.setLeft(node);
                    root.setRight(treeNode);
                    output.add(root);
                }
            }
        }
        dp.get(start).set(end, output);

        return output;
    }
}
