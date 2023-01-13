package org.example.nodes;

import org.example.nodes.models.ListNode;

/*
    You are given two non-empty linked lists representing two non-negative integers.
    The digits are stored in reverse order, and each of their nodes contains a single digit.
    Add the two numbers and return the sum as a linked list.

    You may assume the two numbers do not contain any leading zero,
    except the number 0 itself.
*/
public class AddNumbers {

    public ListNode addTwoNumbers(final ListNode l1, final ListNode l2) {
        return addTwoNumbers(l1, l2, 0);
    }

    private ListNode addTwoNumbers(final ListNode l1, final ListNode l2, final int overflow) {

        if (l1 == null && l2 == null && overflow == 0) {
            return null;
        }
        final int sum = (l1 == null ? 0 : l1.getVal()) + (l2 == null ? 0 : l2.getVal()) + overflow;
        final ListNode newL1 = (l1 == null ? null : l1.getNext());
        final ListNode newL2 = (l2 == null ? null : l2.getNext());
        return new ListNode(sum % 10, addTwoNumbers(newL1, newL2, sum / 10));
    }
}
