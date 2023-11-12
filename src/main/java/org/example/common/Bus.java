package org.example.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bus {

    /*
            You are given an array routes representing bus routes where routes[i] is a bus route that
            the ith bus repeats forever.
                For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the
                    sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
            You will start at the bus stop source (You are not on any bus initially), and you want to go
            to the bus stop target. You can travel between bus stops by buses only.
            Return the least number of buses you must take to travel from source to target. Return -1
            if it is not possible.
     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        List<Integer>[] graph = new ArrayList[1000000];
        for (int i = 0; i < routes.length; i++) {
            int[] arr = routes[i];
            for (int e : arr) {
                if (graph[e] == null) graph[e] = new ArrayList<>();
                graph[e].add(i);
            }
        }
        boolean[] vis = new boolean[1000000];
        boolean[] bus_vis = new boolean[routes.length];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{source, 0});
        while (!q.isEmpty()) {
            int[] e = q.remove();
            int s = e[0];
            int lev = e[1];
            if (s == target) return lev;
            vis[s] = true;
            for (int bus : graph[s]) {
                if (bus_vis[bus]) continue;
                for (int x : routes[bus]) {
                    if (!vis[x]) {
                        q.add(new int[]{x, lev + 1});
                    }
                }
                bus_vis[bus] = true;
            }
        }
        return -1;
    }

}
