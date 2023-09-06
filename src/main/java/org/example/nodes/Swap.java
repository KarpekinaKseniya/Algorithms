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

    /*
        Given the head of a singly linked list and an integer k, split the linked list into k
        consecutive linked list parts.
        The length of each part should be as equal as possible: no two parts should have a size
        differing by more than one. This may lead to some parts being null.
        The parts should be in the order of occurrence in the input list, and parts occurring earlier
        should always have a size greater than or equal to parts occurring later.
        Return an array of the k parts.
    */
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode cur = head;
        ListNode[] answer = new ListNode[k];
        int ptr = 0, listLength = 0;
        while (cur != null) {
            listLength++;
            cur = cur.getNext();
        }
        int extra = listLength % k;
        int partLength = listLength / k;
        cur = head;
        while (cur != null) {
            answer[ptr++] = cur;
            int currentLength = partLength - 1 + ((extra-- > 0) ? 1 : 0);
            for (int i = 0; i < currentLength; i++)
                cur = cur.getNext();
            ListNode temp = cur.getNext();
            cur.setNext(null);
            cur = temp;
        }
        return answer;
    }
}
