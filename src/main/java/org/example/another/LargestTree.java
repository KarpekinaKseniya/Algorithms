package org.example.another;

import org.example.nodes.models.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
    Given the root of a binary tree, return an array of the largest value
    in each row of the tree (0-indexed).
*/
public class LargestTree {

    LinkedList<TreeNode> q = new LinkedList<>();
    List<Integer> l = new ArrayList<>();
    List<Integer> b = new ArrayList<>();

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return new ArrayList<>();
        level(root);
        return l;
    }

    public void level(TreeNode root) {
        TreeNode p = root;
        if (p == null) return;
        q.addLast(p);
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                p = q.removeFirst();
                b.add(p.getVal());

                if (p.getLeft() != null) q.addLast(p.getLeft());
                if (p.getRight() != null) q.addLast(p.getRight());

            }
            l.add(Collections.max(b));
            b = new ArrayList<>();


        }
    }

}
