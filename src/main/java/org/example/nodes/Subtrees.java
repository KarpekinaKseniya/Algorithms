package org.example.nodes;

import org.example.nodes.models.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Subtrees {

    private int cnt = 0;

    /*
        Given the root of a binary tree, return the number of nodes where the value of the node
        is equal to the average of the values in its subtree.
        Note:
            The average of n elements is the sum of the n elements divided by n and rounded down
            to the nearest integer.
            A subtree of root is a tree consisting of root and all of its descendants.
    */
    public int averageOfSubtree(TreeNode root) {
        solve(root);
        return cnt;
    }

    private int[] solve(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};
        int[] left = solve(root.getLeft());
        int[] right = solve(root.getRight());
        int sum = left[0] + right[0] + root.getVal();
        int nodes = left[1] + right[1] + 1;
        if (sum / nodes == root.getVal())
            cnt++;
        return new int[]{sum, nodes};
    }

    /*
        Given the root of a binary tree, return all duplicate subtrees.
        For each kind of duplicate subtrees, you only need to return the root node of any one of them.
        Two trees are duplicate if they have the same structure with the same node values.
    */
    public List<TreeNode> findDuplicateSubtrees(final TreeNode root) {
        final List<TreeNode> result = new LinkedList<>();
        preOrder(root, new HashMap<>(), result);
        return result;
    }

    private String preOrder(final TreeNode cur, final Map<String, Integer> map, final List<TreeNode> res) {
        if (cur == null)
            return "#";
        String serial = cur.getVal() + ",";
        serial += preOrder(cur.getLeft(), map, res) + ",";
        serial += preOrder(cur.getRight(), map, res);

        map.put(serial, map.getOrDefault(serial, 0) + 1);
        if (map.get(serial) == 2)
            res.add(cur);
        return serial;
    }
}
