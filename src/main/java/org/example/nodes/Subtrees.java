package org.example.nodes;

import org.example.nodes.models.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
    Given the root of a binary tree, return all duplicate subtrees.
    For each kind of duplicate subtrees, you only need to return the root node of any one of them.
    Two trees are duplicate if they have the same structure with the same node values.
*/
public class Subtrees {

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
