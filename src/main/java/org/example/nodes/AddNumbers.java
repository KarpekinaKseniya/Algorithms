package org.example.nodes;

import org.example.nodes.models.ListNode;

import java.util.Stack;


public class AddNumbers {

    /*
        You are given two non-empty linked lists representing two non-negative integers.
        The digits are stored in reverse order, and each of their nodes contains a single digit.
        Add the two numbers and return the sum as a linked list.
        You may assume the two numbers do not contain any leading zero,
        except the number 0 itself.
    */
    public ListNode addTwoNumbers(final ListNode l1, final ListNode l2) {
        return addTwoNumbers(l1, l2, 0);
    }

    /*
        You are given two non-empty linked lists representing two non-negative integers.
        The most significant digit comes first and each of their nodes contains a single digit.
        Add the two numbers and return the sum as a linked list.
        You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        Stack<Integer> st3 = new Stack<>();
        while (l1 != null) {
            st1.push(l1.getVal());
            l1 = l1.getNext();
        }
        while (l2 != null) {
            st2.push(l2.getVal());
            l2 = l2.getNext();
        }
        int carry = 0;
        while (!st1.isEmpty() || !st2.isEmpty() || carry != 0) {
            if (!st1.isEmpty()) {
                carry += st1.pop();
            }

            if (!st2.isEmpty()) {
                carry += st2.pop();
            }

            st3.push(carry % 10);
            carry = carry / 10;
        }
        ListNode dummy = new ListNode();
        ListNode itr = dummy;
        while (!st3.isEmpty()) {
            itr.setNext(new ListNode(st3.pop()));
            itr = itr.getNext();
        }
        return dummy.getNext();
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
