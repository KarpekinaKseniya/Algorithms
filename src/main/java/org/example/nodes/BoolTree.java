package org.example.nodes;

import org.example.nodes.models.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BoolTree {

    /*
        You are given the root of a full binary tree with the following properties:
            Leaf nodes have either the value 0 or 1, where 0 represents False and 1 represents True.
            Non-leaf nodes have either the value 2 or 3, where 2 represents the boolean OR and
            3 represents the boolean AND.
        The evaluation of a node is as follows:
            If the node is a leaf node, the evaluation is the value of the node, i.e. True or False.
            Otherwise, evaluate the node's two children and apply the boolean operation of
            its value with the children's evaluations.
            Return the boolean result of evaluating the root node.
            A full binary tree is a binary tree where each node has either 0 or 2 children.
            A leaf node is a node that has zero children.
    */
    public boolean evaluateTree(final TreeNode root) {
        if (root.getVal() == 0) {
            return false;
        }
        if (root.getVal() == 1) {
            return true;
        }
        final boolean left = evaluateTree(root.getLeft());
        final boolean right = evaluateTree(root.getRight());
        return root.getVal() == 2 ? left || right : left && right;
    }

    /*
    Given the root of a binary tree, determine if it is a complete binary tree.
    In a complete binary tree, every level, except possibly the last, is completely filled,
    and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes
    inclusive at the last level h.
     */
    public boolean isCompleteTree(final TreeNode root) {
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.peek() != null) {
            final TreeNode node = queue.poll();
            queue.offer(node.getLeft());
            queue.offer(node.getRight());
        }
        while (!queue.isEmpty() && queue.peek() == null) {
            queue.poll();
        }
        return queue.isEmpty();
    }
}
