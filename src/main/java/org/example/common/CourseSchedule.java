package org.example.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
    You are given an array prerequisites where prerequisites[i] = [a-i, b-i] indicates that you
    must take course bi first if you want to take course a-i.
    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    Return true if you can finish all courses. Otherwise, return false.
*/
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            if (!map.containsKey(prerequisite[0])) {
                map.put(prerequisite[0], new ArrayList<>());
            }
            map.get(prerequisite[0]).add(prerequisite[1]);
        }
        boolean[] marked = new boolean[numCourses];
        boolean[] cleared = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (map.containsKey(i)) {
                if (checkForCycle(i, map, marked, cleared)) return false;
                cleared[i] = true;
            }
        }
        return true;
    }

    public boolean checkForCycle(int courseNum, Map<Integer, List<Integer>> map, boolean[] marked, boolean[] cleared) {
        if (!map.containsKey(courseNum)) return false;
        if (marked[courseNum] && !cleared[courseNum]) return true;
        if (cleared[courseNum]) return false;
        marked[courseNum] = true;
        for (Integer i : map.get(courseNum)) {
            if (checkForCycle(i, map, marked, cleared)) return true;
            cleared[i] = true;
        }
        return false;
    }

}
