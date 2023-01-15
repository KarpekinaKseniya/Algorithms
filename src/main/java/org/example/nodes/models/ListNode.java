package org.example.nodes.models;

import java.util.Objects;

public class ListNode {

    private int val;

    private ListNode next;

    public ListNode() {
    }

    public ListNode(final int val) {
        this.val = val;
    }

    public ListNode(final int val, final ListNode next) {
        this.val = val;
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListNode listNode)) return false;
        return val == listNode.val && Objects.equals(next, listNode.next);
    }
}
