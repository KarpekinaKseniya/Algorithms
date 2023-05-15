package org.example.nodes;

import org.example.nodes.models.ListNode;

/*
    You are given the head of a linked list, and an integer k.
    Return the head of the linked list after swapping the values of the k-th node from the beginning
    and the k-th node from the end (the list is 1-indexed).
*/
public class Swap {

    public ListNode swapNodes(final ListNode head, final int k) {
        ListNode fast = head, slow = head, first = head, second = head;
        for (int i = 0; i < k - 1; ++i) {
            fast = fast.getNext();
        }
        first = fast;
        while (fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext();
        }
        second = slow;
        final int temp = first.getVal();
        first.setVal(second.getVal());
        second.setVal(temp);
        return head;
    }

}
