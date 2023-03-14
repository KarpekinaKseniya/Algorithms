package org.example.nodes;

import org.example.nodes.models.TreeNode;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/*
    You are given the root of a binary tree containing digits from 0 to 9 only.
    Each root-to-leaf path in the tree represents a number.
    For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
    Return the total sum of all root-to-leaf numbers.
    Test cases are generated so that the answer will fit in a 32-bit integer.
    A leaf node is a node with no children.
*/
public class SumLeafNumbers {

    public int sumNumbers(final TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return root.getVal();
        }

        final Deque<Map.Entry<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new AbstractMap.SimpleEntry<>(root, root.getVal()));

        int sum = 0;

        while (!stack.isEmpty()) {
            final Map.Entry<TreeNode, Integer> cur = stack.pop();
            final TreeNode node = cur.getKey();
            final int num = cur.getValue();

            if (node.getLeft() == null && node.getRight() == null) {
                sum += num;
                continue;
            }

            if (node.getLeft() != null) {
                stack.push(new AbstractMap.SimpleEntry<>(node.getLeft(), num * 10 + node.getLeft().getVal()));
            }
            if (node.getRight() != null) {
                stack.push(new AbstractMap.SimpleEntry<>(node.getRight(), num * 10 + node.getRight().getVal()));
            }
        }

        return sum;
    }
}
