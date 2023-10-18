package org.example.common;

import java.util.ArrayList;
import java.util.List;

/*
    You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also
    given a 2D integer array relations where relations[j] = [prevCoursej, nextCoursej] denotes that
    course prevCoursej has to be completed before course nextCoursej (prerequisite relationship).
    Furthermore, you are given a 0-indexed integer array time where time[i] denotes how many months it
    takes to complete the (i+1)th course.
    You must find the minimum number of months needed to complete all the courses following these rules:
        You may start taking a course at any time if the prerequisites are met.
        Any number of courses can be taken at the same time.
        Return the minimum number of months needed to complete all the courses.
    Note: The test cases are generated such that it is possible to complete every course (i.e., the graph
    is a directed acyclic graph).
*/
public class ParallelCourses {

    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] a = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            a[i] = new ArrayList<>();
        }
        for (int[] x : relations) {
            a[x[0]].add(x[1]);
        }

        Integer[] memo = new Integer[n + 1];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(dfs(i, memo, a, time), ans);
        }
        return ans;
    }


    private int dfs(int curr, Integer[] memo, List<Integer>[] adj, int[] time) {
        if (memo[curr] != null) {
            return memo[curr];
        }
        int max = 0;
        for (int x : adj[curr]) {
            max = Math.max(max, dfs(x, memo, adj, time));
        }
        return memo[curr] = max + time[curr - 1];
    }

}
