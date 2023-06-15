package org.example.nodes;

import org.example.nodes.models.ListNode;
import org.example.nodes.models.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TwinSum {

    /*
        In a linked list of size n, where n is even, the ith node (0-indexed) of
        the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
        For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2.
        These are the only nodes with twins for n = 4.
        The twin sum is defined as the sum of a node and its twin.
        Given the head of a linked list with even length, return the maximum twin sum of the linked list.
    */
    public int pairSum(final ListNode head) {
        final Stack<ListNode> st = new Stack<>();
        int len = 0;
        int i = 0;
        int ans = Integer.MIN_VALUE;
        ListNode ptr = head;
        while (ptr != null) {
            len++;
            ptr = ptr.getNext();
        }
        ptr = head;
        while (i < len / 2) {
            st.push(ptr);
            ptr = ptr.getNext();
            i++;
        }
        while (ptr != null) {
            ans = Math.max(ans, ptr.getVal() + st.pop().getVal());
            ptr = ptr.getNext();
        }
        return ans;
    }

    /*
        Given the root of a binary tree, the level of its root is 1, the level of its children is 2,
        and so on.
        Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
    */
    public int maxLevelSum(final TreeNode root) {
        if (root == null) {
            return 0;
        }
        final Queue<TreeNode> queue = new LinkedList<>();
        int minLevel = 0;
        int maxSum = Integer.MIN_VALUE;
        queue.add(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            while (size > 0) {
                TreeNode node = queue.poll();
                sum += node.getVal();
                if (node.getLeft() != null)
                    queue.add(node.getLeft());
                if (node.getRight() != null)
                    queue.add(node.getRight());

                size--;
            }
            if (maxSum < sum) {
                maxSum = sum;
                minLevel = level;
            }
            level++;
        }
        return minLevel;
    }
}
