package org.example.nodes;

import org.example.nodes.models.ListNode;

/*
    Given the head of a linked list and a value x, partition it such that all nodes less
    than x come before nodes greater than or equal to x.
    You should preserve the original relative order of the nodes in each of the two partitions.
*/
public class PartitionList {

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.getNext() == null) return head;
        ListNode lessCurr = null, xCurr = null, current = head;
        ListNode lessHead = null, xHead = null;
        while (current != null) {
            if (current.getVal() < x) {
                if (lessCurr == null) {
                    lessCurr = current;
                    lessHead = lessCurr;
                } else {
                    lessCurr.setNext(current);
                    lessCurr = lessCurr.getNext();
                }
            } else {
                if (xCurr == null) {
                    xCurr = current;
                    xHead = xCurr;
                } else {
                    xCurr.setNext(current);
                    xCurr = xCurr.getNext();
                }
            }
            current = current.getNext();
        }
        if (xCurr != null) xCurr.setNext(null);
        if (lessCurr != null) lessCurr.setNext(xHead);
        return lessHead != null ? lessHead : xHead;
    }

}
