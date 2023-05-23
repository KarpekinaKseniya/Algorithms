package org.example.another;

import java.util.PriorityQueue;
import java.util.Queue;

/*
    Design a class to find the k-th largest element in a stream.
    Note that it is the k-th largest element in the sorted order, not the k-th distinct element.
    Implement KthLargest class:
        KthLargest(int k, int[] nums) Initializes the object with the integer k and
        the stream of integers nums.
        int add(int val) Appends the integer val to the stream and returns the element
        representing the k-th largest element in the stream.
*/
public class KthLargest {

    private final Queue<Integer> heap = new PriorityQueue<>();
    private final int k;

    public KthLargest(final int k, final int[] nums) {
        this.k = k;
        for (var n : nums) add(n);
    }

    public int add(final int val) {
        heap.offer(val);
        if (heap.size() > k) {
            heap.poll();
        }
        return heap.peek();
    }

}
