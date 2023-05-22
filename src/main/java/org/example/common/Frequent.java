package org.example.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
    Given an integer array nums and an integer k, return the k most frequent elements.
    You may return the answer in any order.
*/
public class Frequent {

    public int[] topKFrequent(final int[] nums, final int k) {
        final Map<Integer, Integer> countMap = new HashMap<>();
        final Map<Integer, List<Integer>> sortMap = new TreeMap<>((a, b) -> b - a);
        for (final int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        for (final int key : countMap.keySet()) {
            final int value = countMap.get(key);
            List<Integer> numList = sortMap.getOrDefault(value, new ArrayList<>());
            numList.add(key);
            sortMap.put(value, numList);
        }
        final List<Integer> res = new ArrayList<>();
        for (final List<Integer> l : sortMap.values()) {
            if (res.size() < k) {
                res.addAll(l);
            }
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

}
