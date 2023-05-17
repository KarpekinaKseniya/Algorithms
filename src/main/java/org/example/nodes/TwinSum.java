package org.example.nodes;

import org.example.nodes.models.ListNode;

import java.util.Stack;

/*
    In a linked list of size n, where n is even, the ith node (0-indexed) of
    the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
    For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2.
    These are the only nodes with twins for n = 4.
    The twin sum is defined as the sum of a node and its twin.
    Given the head of a linked list with even length, return the maximum twin sum of the linked list.
*/
public class TwinSum {

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
}
