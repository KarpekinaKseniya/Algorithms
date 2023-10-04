package org.example.another;

import java.util.Arrays;

/*
    Design a HashMap without using any built-in hash table libraries.
    Implement the MyHashMap class:
        MyHashMap() initializes the object with an empty map.
        void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already
            exists in the map, update the corresponding value.
        int get(int key) returns the value to which the specified key is mapped, or -1 if this map
            contains no mapping for the key.
        void remove(key) removes the key and its corresponding value if the map contains the mapping
            for the key.
*/
public class MyHashMap {

    int[] data;
    public MyHashMap() {
        data = new int[1000001];
        Arrays.fill(data, -1);
    }
    public void put(int key, int val) {
        data[key] = val;
    }
    public int get(int key) {
        return data[key];
    }
    public void remove(int key) {
        data[key] = -1;
    }

}
