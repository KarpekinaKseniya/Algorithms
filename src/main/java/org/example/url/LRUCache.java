package org.example.url;

import java.util.LinkedHashMap;
import java.util.Map;

/*
    Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
    Implement the LRUCache class:
        LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
        int get(int key) Return the value of the key if the key exists, otherwise return -1.
        void put(int key, int value) Update the value of the key if the key exists.
        Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity
        from this operation, evict the least recently used key.
    The functions get and put must each run in O(1) average time complexity.
*/
public class LRUCache {

    private final int cap;
    private final Map<Integer, Integer> cache = new LinkedHashMap<>();

    public LRUCache(final int capacity) {
        cap = capacity;
    }

    public void makeRecently(final int key) {
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }


    public int get(final int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return cache.get(key);
    }

    public void put(final int key, final int value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
            cache.put(key, value);
            return;
        }
        if (cache.size() >= cap) {
            Integer oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);

        }
        cache.put(key, value);
    }

}
