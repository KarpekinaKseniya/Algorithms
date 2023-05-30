package org.example.another;

import java.util.ArrayList;
import java.util.List;

/*
    Design a HashSet without using any built-in hash table libraries.
    Implement MyHashSet class:
        void add(key) Inserts the value key into the HashSet.
        bool contains(key) Returns whether the value key exists in the HashSet or not.
        void remove(key) Removes the value key in the HashSet.
        If key does not exist in the HashSet, do nothing.
*/
public class MyHashSet {

    private List<Integer> list;

    public MyHashSet() {
        list = new ArrayList<>();
    }

    public void add(final int key) {
        if (!list.contains(key))
            list.add(key);
    }

    public void remove(final int key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == key)
                list.remove(i);
        }
    }

    public boolean contains(final int key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == key)
                return true;
        }
        return false;
    }

}
