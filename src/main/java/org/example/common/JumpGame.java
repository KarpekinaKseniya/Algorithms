package org.example.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class JumpGame {

    /*
        You are given a 0-indexed array of integers nums of length n.
        You are initially positioned at nums[0].
        Each element nums[i] represents the maximum length of a forward jump from index i.
        In other words, if you are at nums[i], you can jump to any nums[i + j] where:
            0 <= j <= nums[i] and
            i + j < n
        Return the minimum number of jumps to reach nums[n - 1].
        The test cases are generated such that you can reach nums[n - 1].
    */
    public int jump(final int[] nums) {
        int score = 0;
        int num = 0;
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == num) {
                score++;
                num = max;
            }
        }
        return score;
    }

    /*
        Given an array of integers arr, you are initially positioned at the first index of the array.
        In one step you can jump from index i to index:
            i + 1 where: i + 1 < arr.length.
            i - 1 where: i - 1 >= 0.
            j where: arr[i] == arr[j] and i != j.
        Return the minimum number of steps to reach the last index of the array.
        Notice that you can not jump outside of the array at any time.
    */
    public int minJumps(final int[] arr) {
        final int len = arr.length;
        final Map<Integer, List<Integer>> indicesOfValue = new HashMap<>();
        for (int i = 0; i < len; i++)
            indicesOfValue.computeIfAbsent(arr[i], x -> new LinkedList<>()).add(i);
        boolean[] visited = new boolean[len];
        visited[0] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int step = 0;
        while (!q.isEmpty()) {
            for (int size = q.size(); size > 0; --size) {
                int i = q.poll();
                if (i == len - 1) return step;
                List<Integer> next = indicesOfValue.get(arr[i]);
                next.add(i - 1);
                next.add(i + 1);
                for (int j : next) {
                    if (j >= 0 && j < len && !visited[j]) {
                        visited[j] = true;
                        q.offer(j);
                    }
                }
                next.clear();
            }
            step++;
        }
        return 0;
    }
}
