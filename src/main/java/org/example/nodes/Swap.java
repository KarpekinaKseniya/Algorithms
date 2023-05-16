package org.example.nodes;

import org.example.nodes.models.ListNode;

public class Swap {

    /*
        You are given the head of a linked list, and an integer k.
        Return the head of the linked list after swapping the values of the k-th node from the beginning
        and the k-th node from the end (the list is 1-indexed).
    */
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

    /*
        Given a linked list, swap every two adjacent nodes and return its head.
        You must solve the problem without modifying the values in the list's
        nodes (i.e., only nodes themselves may be changed.)
    */
    public ListNode swapPairs(final ListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        ListNode nodeNextNext = swapPairs(head.getNext().getNext());
        ListNode tmp = head.getNext();
        head.setNext(nodeNextNext);
        tmp.setNext(head);
        return tmp;
    }
}
