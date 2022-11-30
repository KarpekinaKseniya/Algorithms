package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
    Given an array of integers arr,
    return true if the number of occurrences of each value in the array is unique, or false otherwise.
 */
class Occurrences {

    public boolean uniqueOccurrences(final int[] arr) {
        if (arr.length == 1) {
            return true;
        }
        final Map<Integer, Integer> map = new HashMap<>();
        for (final int value : arr) {
            map.merge(value, 1, Integer::sum);
        }
        final Set<Integer> set = new HashSet<>(map.values());
        return map.size() == set.size();
    }
}
