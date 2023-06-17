package org.example.common;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

/*
    Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero)
    needed to make arr1 strictly increasing.
    In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and
    do the assignment arr1[i] = arr2[j].
    If there is no way to make arr1 strictly increasing, return -1.
*/
public class Strictly {

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Queue<int[]> q = new PriorityQueue<>(1000, (int[] a, int[] b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            if (a[0] != b[0]) return b[0] - a[0];
            return a[2] - b[2];
        });
        TreeSet<Integer> nums2 = new TreeSet<>();
        for (int n : arr2) nums2.add(n);

        q.add(new int[]{0, 1, nums2.first()});
        q.add(new int[]{0, 0, arr1[0]});

        int[] values = new int[arr1.length];
        Arrays.fill(values, Integer.MAX_VALUE);

        while (!q.isEmpty()) {
            int t[] = q.poll();
            int id = t[0], ch = t[1], val = t[2];
            if (id == arr1.length - 1) return ch;

            if (values[id] <= val) continue;
            values[id] = val;

            if (arr1[++id] > val) q.add(new int[]{id, ch, arr1[id]});
            Integer num = nums2.higher(val);
            if (num != null)
                q.add(new int[]{id, ch + 1, num});
        }

        return -1;
    }

}
