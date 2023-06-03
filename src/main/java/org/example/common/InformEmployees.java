package org.example.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    A company has n employees with a unique ID for each employee from 0 to n - 1.
    The head of the company is the one with headID.
    Each employee has one direct manager given in the manager array where manager[i]
    is the direct manager of the i-th employee, manager[headID] = -1.
    Also, it is guaranteed that the subordination relationships have a tree structure.
    The head of the company wants to inform all the company employees of an urgent piece of news.
    He will inform his direct subordinates, and they will inform their subordinates, and so
    on until all employees know about the urgent news.
    The i-th employee needs informTime[i] minutes to inform all of his direct
    subordinates (i.e., After informTime[i] minutes, all his direct subordinates
    can start spreading the news).
    Return the number of minutes needed to inform all the employees about the urgent news.
*/
public class InformEmployees {

    public int numOfMinutes(final int n, final int headID, final int[] manager, final int[] informTime) {
        final List<Integer>[] parentChilds = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            if (i == headID) continue;
            final int parent = manager[i];
            if (parentChilds[parent] == null) parentChilds[parent] = new ArrayList<>();
            parentChilds[parent].add(i);
        }
        final Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{headID, informTime[headID]});
        int maxInformTime = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currParent = curr[0];
            int currInformTime = curr[1];
            maxInformTime = Math.max(maxInformTime, currInformTime);
            final List<Integer> childs = parentChilds[currParent];
            if (childs != null) {
                for (final Integer child : childs) {
                    queue.offer(new int[]{child, currInformTime + informTime[child]});
                }
            }
        }
        return maxInformTime;
    }

}
