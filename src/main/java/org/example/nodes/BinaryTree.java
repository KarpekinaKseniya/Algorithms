package org.example.nodes;

import org.example.nodes.models.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTree {

    private static Map<Integer, Integer> hp;

    private void helper(TreeNode root) {
        if (root == null) return;
        hp.put(root.getVal(), hp.getOrDefault(root.getVal(), 0) + 1);
        helper(root.getLeft());
        helper(root.getRight());
    }

    /*
        Given the root of a binary search tree (BST) with duplicates, return all the
        mode(s) (i.e., the most frequently occurred element) in it.
        If the tree has more than one mode, return them in any order.
        Assume a BST is defined as follows:
        The left subtree of a node contains only nodes with keys less than or equal to the node's key.
        The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
        Both the left and right subtrees must also be binary search trees.
    */
    public int[] findMode(TreeNode root) {
        hp = new HashMap<>();
        helper(root);

        int max = Integer.MIN_VALUE;
        for (var a : hp.values()) max = Math.max(max, a);
        int n = 0;
        for (var a : hp.values()) {
            if (a == max) n++;
        }

        int ans[] = new int[n];
        int i = 0;
        for (var a : hp.keySet()) {
            if (hp.get(a) == max) ans[i++] = a;
        }
        return ans;
    }

    /*
        Given an integer n, return a list of all possible full binary trees with n nodes.
        Each node of each tree in the answer must have Node.val == 0.
        Each element of the answer is the root node of one possible tree. You may return
        the final list of trees in any order.
        A full binary tree is a binary tree where each node has exactly 0 or 2 children.
    */
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

    /*
        Given the root of a binary tree, construct a string consisting of parenthesis and integers
        from a binary tree with the preorder traversal way, and return it.
        Omit all the empty parenthesis pairs that do not affect the one-to-one mapping relationship
        between the string and the original binary tree.
    */
    public String tree2str(TreeNode root) {
        if (root == null) return "";
        var s = Integer.toString(root.getVal());
        if (root.getLeft() == null && root.getRight() == null)
            return s;
        s += "(" + tree2str(root.getLeft()) + ")";
        if (root.getRight() != null)
            s += "(" + tree2str(root.getRight()) + ")";
        return s;
    }
}
