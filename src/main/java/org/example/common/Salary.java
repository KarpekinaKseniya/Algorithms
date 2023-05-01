package org.example.common;

/*
    You are given an array of unique ints salary where salary[i]
    is the salary of the ith employee.
    Return the average salary of employees excluding the minimum and maximum salary.
    Answers within 10-5 of the actual answer will be accepted.
*/
public class Salary {

    public double average(final int[] salary) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (final int s : salary) {
            sum += s;
            min = Math.min(min, s);
            max = Math.max(max, s);
        }
        return (double) (sum - min - max) / (salary.length - 2);
    }
}
