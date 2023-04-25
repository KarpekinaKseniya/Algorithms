package org.example.common;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*
    You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].
    Implement the SmallestInfiniteSet class:
        SmallestInfiniteSet() Initializes the SmallestInfiniteSet object
            to contain all positive integers.
        int popSmallest() Removes and returns the smallest integer contained in the infinite set.
        void addBack(int num) Adds a positive integer num back into the infinite set,
            if it is not already in the infinite set.

*/
public class SmallestInfiniteSet {

    private Queue<Integer> pq;
    private Set<Integer> visited;
    private int start;

    public SmallestInfiniteSet() {
        pq = new PriorityQueue<>();
        visited = new HashSet<>();
        start = 1;
    }

    public int popSmallest() {
        if (pq.isEmpty()) {
            return start++;
        } else {
            if (start < pq.peek()) {
                return start++;
            } else {
                final int smallest = pq.poll();
                if (start == smallest) start++;
                visited.remove(smallest);
                return smallest;
            }
        }
    }

    public void addBack(final int num) {
        if (!visited.contains(num)) {
            visited.add(num);
            pq.offer(num);
        }
    }

}
