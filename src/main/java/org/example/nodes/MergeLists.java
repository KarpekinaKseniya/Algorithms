package org.example.nodes;

import org.example.nodes.models.ListNode;

import java.util.PriorityQueue;

public class MergeLists {

    /*
        You are given the heads of two sorted linked lists list1 and list2.
        Merge the two lists in a one sorted list.
        The list should be made by splicing together the nodes of the first two lists.
        Return the head of the merged linked list.
    */
    public ListNode mergeTwoLists(final ListNode list1, final ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.getVal() < list2.getVal()) {
            list1.setNext(mergeTwoLists(list1.getNext(), list2));
            return list1;
        } else {
            list2.setNext(mergeTwoLists(list1, list2.getNext()));
            return list2;
        }
    }

    /*
        You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
        Merge all the linked-lists into one sorted linked-list and return it.
     */
    public ListNode mergeKLists(final ListNode[] lists) {
        final PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (final ListNode list : lists) {
            ListNode current = list;
            while (current != null) {
                queue.add(current.getVal());
                current = current.getNext();
            }
        }
        ListNode head = new ListNode();
        ListNode temp = head;
        while (queue.size() > 0) {
            temp.setNext(new ListNode(queue.remove()));
            temp = temp.getNext();
        }
        return head.getNext();
    }
}
