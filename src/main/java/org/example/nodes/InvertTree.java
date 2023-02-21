package org.example.nodes;

import org.example.nodes.models.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class InvertTree {

    // Given the root of a binary tree, invert the tree, and return its root.
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

    /*
    Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
    (i.e., from left to right, then right to left for the next level and alternate between).
    */
    public List<List<Integer>> zigzagLevelOrder(final TreeNode root) {
        final LinkedList<List<Integer>> result = new LinkedList<>();
        final Deque<TreeNode> deque = new LinkedList<>();
        if (root == null) {
            return result;
        }
        int level = 0;
        deque.push(root);
        while (!deque.isEmpty()) {
            final int size = deque.size();
            final List<Integer> app = new LinkedList<>();
            if (level % 2 == 0) {
                for (int i = 0; i < size; i++) {
                    TreeNode temp = deque.getFirst();
                    deque.removeFirst();
                    app.add(temp.getVal());
                    if (temp.getLeft() != null) {
                        deque.addLast(temp.getLeft());
                    }
                    if (temp.getRight() != null) {
                        deque.addLast(temp.getRight());
                    }
                }
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode temp = deque.getLast();
                    deque.removeLast();
                    app.add(temp.getVal());
                    if (temp.getRight() != null) {
                        deque.addFirst(temp.getRight());
                    }
                    if (temp.getLeft() != null) {
                        deque.addFirst(temp.getLeft());
                    }
                }
            }
            result.add(app);
            level++;
        }
        return result;
    }
}