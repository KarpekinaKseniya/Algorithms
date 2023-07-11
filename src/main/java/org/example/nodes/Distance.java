package org.example.nodes;

import org.example.nodes.models.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
    Given the root of a binary tree, the value of a target node target, and an integer k,
    return an array of the values of all nodes that have a distance k from the target node.
    You can return the answer in any order.
*/
public class Distance {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        Map<TreeNode, Integer> hm = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            level++;
            while (size-- > 0) {
                TreeNode curr = q.poll();
                hm.put(curr, level);
                if (curr.getLeft() != null) {
                    q.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    q.add(curr.getRight());
                }

            }
        }
        for (Map.Entry<TreeNode, Integer> entry : hm.entrySet()) {
            TreeNode lcs = lcs(root, target, entry.getKey());
            if ((hm.get(entry.getKey()) - hm.get(lcs)) + (hm.get(target) - hm.get(lcs)) == k) {
                ans.add(entry.getKey().getVal());
            }
        }
        return ans;
    }

    //Lowest Common Ancestor
    TreeNode lcs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lcs(root.getLeft(), p, q);
        TreeNode right = lcs(root.getRight(), p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;

    }

}
