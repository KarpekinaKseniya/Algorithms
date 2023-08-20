package org.example.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
    There are n items each belonging to zero or one of m groups where group[i] is the group that
    the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group. The items and
    the groups are zero indexed. A group can have no item belonging to it.
    Return a sorted list of the items such that:
        The items that belong to the same group are next to each other in the sorted list.
        There are some relations between these items where beforeItems[i] is a list containing
        all the items that should come before the i-th item in the sorted array (to the left of
        the i-th item).
    Return any solution if there is more than one solution and return an empty list if there is
    no solution.
*/
public class RespectingDep {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, List<Integer>> groupGraph = new HashMap<>();
        Map<Integer, List<Integer>> inGroupGraph = new HashMap<>();
        Map<Integer, Integer> groupDegrees = new HashMap<>();
        int[] arrDegrees = new int[n];
        int[] gr = new int[n];
        for (int i = 0; i < n; i++) {
            if (group[i] > -1)
                gr[i] = group[i];
            else
                gr[i] = i + m + 1;
            inGroupGraph.put(i, new ArrayList<>());
            groupDegrees.putIfAbsent(gr[i], 0);
            groupGraph.putIfAbsent(gr[i], new ArrayList<>());
        }
        for (int i = 0; i < beforeItems.size(); i++) {
            map.putIfAbsent(gr[i], new ArrayList<>());
            map.get(gr[i]).add(i);
            List<Integer> list = beforeItems.get(i);
            for (int x : list) {
                if (gr[i] == gr[x]) {
                    inGroupGraph.get(x).add(i);
                    arrDegrees[i]++;
                } else {
                    groupGraph.get(gr[x]).add(gr[i]);
                    groupDegrees.put(gr[i], groupDegrees.getOrDefault(gr[i], 0) + 1);
                }
            }
        }
        int[] result = new int[n];
        int index = 0;
        Queue<Integer> q = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : groupDegrees.entrySet()) {
            if (entry.getValue() == 0)
                q.add(entry.getKey());
        }
        while (!q.isEmpty()) {
            int num = q.poll();
            for (int x : groupGraph.get(num)) {
                groupDegrees.put(x, groupDegrees.get(x) - 1);
                if (groupDegrees.get(x) == 0)
                    q.add(x);
            }
            Queue<Integer> queue = new LinkedList<>();
            for (Integer y : map.get(num)) {
                if (arrDegrees[y] == 0)
                    queue.add(y);
            }
            while (!queue.isEmpty()) {
                int temp = queue.poll();
                result[index++] = temp;
                for (Integer y : inGroupGraph.get(temp)) {
                    arrDegrees[y]--;
                    if (arrDegrees[y] == 0)
                        queue.add(y);
                }
            }

        }
        if (index != arrDegrees.length)
            return new int[]{};
        return result;
    }

}
