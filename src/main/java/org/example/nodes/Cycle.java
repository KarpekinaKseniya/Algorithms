package org.example.nodes;

import org.example.nodes.models.ListNode;

/*
    Given the head of a linked list, return the node where the cycle begins.
    If there is no cycle, return null.
    There is a cycle in a linked list if there is some node in the list
    that can be reached again by continuously following the next pointer.
    Internally, pos is used to denote the index of the node that tail's
    next pointer is connected to (0-indexed). It is -1 if there is no cycle.
    Note that pos is not passed as a parameter.
    Do not modify the linked list.
*/
public class Cycle {

    public ListNode detectCycle(final ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow) {
                slow = head;
                while (slow != fast) {
                    fast = fast.getNext();
                    slow = slow.getNext();
                }
                return slow;
            }
        }
        return null;
    }

    /*
        Given head, the head of a linked list, determine if the linked list has a cycle in it.
        There is a cycle in a linked list if there is some node in the list that can be reached again
        by continuously following the next pointer. Internally, pos is used to denote the index of
        the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
        Return true if there is a cycle in the linked list. Otherwise, return false.
    */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
