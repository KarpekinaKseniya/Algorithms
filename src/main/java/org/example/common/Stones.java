package org.example.common;

import java.util.PriorityQueue;
import java.util.Queue;

/*
    You are given an array of integers stones where stones[i] is the weight of the - stone.
    We are playing a game with the stones. On each turn, we choose the heaviest two stones and
    smash them together. Suppose the heaviest two stones have weights x and y with x <= y.
    The result of this smash is:
        If x == y, both stones are destroyed, and
        If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
        At the end of the game, there is at most one stone left.
    Return the weight of the last remaining stone. If there are no stones left, return 0.
*/
public class Stones {

    public int lastStoneWeight(final int[] stones) {
        final Queue<Integer> maxHeap = new PriorityQueue<>((stone1, stone2) -> stone2 - stone1);
        for (final int stone : stones) {
            maxHeap.add(stone);
        }
        while (!maxHeap.isEmpty()) {
            final int y = maxHeap.poll();
            if (maxHeap.isEmpty()) {
                return y;
            } else {
                final int x = maxHeap.poll();
                if (x != y) {
                    maxHeap.add(y - x);
                }
            }
        }
        return 0;
    }

}
