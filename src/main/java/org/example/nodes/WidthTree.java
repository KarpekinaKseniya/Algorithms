package org.example.nodes;

import org.example.nodes.models.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/*
    Given the root of a binary tree, return the maximum width of the given tree.

    The maximum width of a tree is the maximum width among all levels.

    The width of one level is defined as the length between the end-nodes
        (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes
        that would be present in a complete binary tree extending down to that level are also counted
        into the length calculation.

    It is guaranteed that the answer will in the range of a 32-bit signed integer.
*/
public class WidthTree {

    static class Pair {
        int index;
        TreeNode node;

        public Pair(final int index, final TreeNode node) {
            this.index = index;
            this.node = node;
        }
    }

    public int widthOfBinaryTree(final TreeNode root) {
        final Deque<Pair> deque = new LinkedList<>();
        int result = 0;
        if (root != null) deque.offer(new Pair(0, root));
        while (!deque.isEmpty()) {
            final int n = deque.size();
            result = Math.max(result, deque.peekLast().index - deque.peekFirst().index + 1);
            for (int i = 0; i < n; i++) {
                Pair p = deque.poll();
                TreeNode node = p.node;
                int index = p.index;
                if (node.getLeft() != null) deque.offer(new Pair(2 * index, (node.getLeft())));
                if (node.getRight() != null) deque.offer(new Pair(2 * index + 1, (node.getRight())));
            }
        }
        return result;
    }

}
