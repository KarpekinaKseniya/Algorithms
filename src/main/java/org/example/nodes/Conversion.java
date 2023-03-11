package org.example.nodes;

import org.example.nodes.models.ListNode;
import org.example.nodes.models.TreeNode;

/*
    Given the head of a singly linked list where elements are sorted in ascending order,
    convert it to a height-balanced binary search tree.
*/
public class Conversion {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        if (head.getNext() == null) {
            return new TreeNode(head.getVal());
        }
        ListNode mid = findMid(head);
        TreeNode node = new TreeNode(mid.getVal());
        node.setLeft(sortedListToBST(head));
        node.setRight(sortedListToBST(mid.getNext()));
        return node;

    }

    private ListNode findMid(ListNode start) {

        ListNode fast = start;
        ListNode prev = start;

        while (fast != null && fast.getNext() != null) {
            prev = start;
            start = start.getNext();
            fast = fast.getNext().getNext();
        }
        prev.setNext(null);
        return start;
    }
}
