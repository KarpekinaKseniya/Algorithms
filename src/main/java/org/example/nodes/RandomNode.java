package org.example.nodes;

import org.example.nodes.models.ListNode;

import java.util.ArrayList;
import java.util.List;

/*
    Given a singly linked list, return a random node's value from the linked list.
    Each node must have the same probability of being chosen.
    Implement the Solution class:
        Solution(ListNode head) Initializes the object with the head of the singly-linked list head.
        int getRandom() Chooses a node randomly from the list and returns its value.
        All the nodes of the list should be equally likely to be chosen.
*/
public class RandomNode {

    final List<Integer> list = new ArrayList<>();

    public RandomNode(ListNode head) {

        while (head != null) {
            list.add(head.getVal());
            head = head.getNext();
        }
    }

    public int getRandom() {
        int random_index = (int) ((Math.random() * (list.size())));
        return list.get(random_index);
    }
}
