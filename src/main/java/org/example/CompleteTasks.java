package org.example;

import java.util.HashMap;
import java.util.Map;

/*
    You are given a 0-indexed integer array tasks,
    where tasks[i] represents the difficulty level of a task.
    In each round, you can complete either 2 or 3 tasks of the same difficulty level.

    Return the minimum rounds required to complete all the tasks,
    or -1 if it is not possible to complete all the tasks.
*/
public class CompleteTasks {

    public int minimumRounds(final int[] tasks) {
        int rounds = 0;
        final Map<Integer, Integer> map = new HashMap<>();
        for (final int x : tasks) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (final int x : map.keySet()) {
            if (map.get(x) == 1) {
                return -1;
            } else {
                rounds += map.get(x) / 3;
                if (map.get(x) % 3 != 0) {
                    rounds++;
                }
            }
        }
        return rounds;
    }

}
