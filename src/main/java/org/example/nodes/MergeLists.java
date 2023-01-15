package org.example.nodes;

import org.example.nodes.models.ListNode;

/*
    You are given the heads of two sorted linked lists list1 and list2.
    Merge the two lists in a one sorted list.
    The list should be made by splicing together the nodes of the first two lists.
    Return the head of the merged linked list.
*/
public class MergeLists {

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
}
