package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    There are n people that are split into some unknown number of groups.
    Each person is labeled with a unique ID from 0 to n - 1.

    You are given an integer array groupSizes, where groupSizes[i] is the size of the group
    that person i is in. For example, if groupSizes[1] = 3, then person 1 must be in a group of size 3.

    Return a list of groups such that each person i is in a group of size groupSizes[i].

    Each person should appear in exactly one group, and every person must be in a group.
    If there are multiple answers, return any of them.
    It is guaranteed that there will be at least one valid solution for the given input.
*/
public class Groups {

    public List<List<Integer>> groupThePeople(final int[] groupSizes) {
        final Map<Integer, List<Integer>> map = new HashMap<>();
        for (final int size : groupSizes) {
            map.putIfAbsent(size, new ArrayList<>());
        }
        final List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            map.get(groupSizes[i]).add(i);
            if (map.get(groupSizes[i]).size() == groupSizes[i]) {
                result.add(new ArrayList<>(map.get(groupSizes[i])));
                map.put(groupSizes[i], new ArrayList<>());
            }
        }
        return result;
    }
}
