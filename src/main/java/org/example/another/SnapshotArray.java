package org.example.another;

import java.util.ArrayList;
import java.util.List;

/*
    Implement a SnapshotArray that supports the following interface:
        SnapshotArray(int length) initializes an array-like data structure with the given length.
            Initially, each element equals 0.
        void set(index, val) sets the element at the given index to be equal to val.
        int snap() takes a snapshot of the array and returns the snap_id: the total number of
            times we called snap() minus 1.
        int get(index, snap_id) returns the value at the given index, at the time we took
            the snapshot with the given snap_id
*/
public class SnapshotArray {

    private List<int[]>[] h;
    private int snap;

    public SnapshotArray(final int length) {
        h = new List[length];
        for (int i = 0; i < length; i++) {
            h[i] = new ArrayList<>();
            h[i].add(new int[]{-1, 0});
        }
    }

    public void set(final int index, final int val) {
        h[index].add(new int[]{snap, val});
    }

    public int snap() {
        return snap++;
    }

    public int get(final int index, final int snap_id) {
        List<int[]> temp = h[index];
        int l = 0, r = temp.size() - 1;
        while (l < r) {
            int m = r - (r - l) / 2;
            if (temp.get(m)[0] <= snap_id) l = m;
            else r = m - 1;
        }
        return temp.get(l)[1];
    }
}
