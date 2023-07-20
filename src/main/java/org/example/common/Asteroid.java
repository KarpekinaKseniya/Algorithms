package org.example.common;

import java.util.Stack;

public class Asteroid {

    /*
        We are given an array asteroids of integers representing asteroids in a row.
        For each asteroid, the absolute value represents its size, and the sign represents
        its direction (positive meaning right, negative meaning left).
        Each asteroid moves at the same speed.
        Find out the state of the asteroids after all collisions. If two asteroids meet,
        the smaller one will explode. If both are the same size, both will explode.
        Two asteroids moving in the same direction will never meet.
    */
    public int[] asteroidCollision(final int[] asteroids) {
        if (asteroids.length == 0) return asteroids;
        Stack<Integer> s = new Stack<>();
        for (final int a : asteroids) {
            while (true) {
                if (s.size() == 0) {
                    s.push(a);
                    break;
                } else {
                    int prev = s.peek();
                    if (!(prev > 0 && a < 0)) {
                        s.push(a);
                        break;
                    }
                    if (Math.abs(prev) == Math.abs(a)) {
                        s.pop();
                        break;
                    } else {
                        if (Math.abs(prev) < Math.abs(a)) {
                            s.pop();
                        } else if (Math.abs(prev) > Math.abs(a)) {
                            break;
                        }
                    }
                }
            }
        }
        int n = s.size();
        int[] arr = new int[n];
        while (n > 0) {
            arr[--n] = s.pop();
        }
        return arr;
    }

}
