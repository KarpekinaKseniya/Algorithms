package org.example.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/*
    You are given an array of variable pairs equations and an array of real numbers values,
    where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
    Each Ai or Bi is a string that represents a single variable.
    You are also given some queries, where queries[j] = [Cj, Dj] represents the j-th query where you
    must find the answer for Cj / Dj = ?.
    Return the answers to all queries. If a single answer cannot be determined, return -1.0.
    Note: The input is always valid. You may assume that evaluating the queries will not result in
    division by zero and that there is no contradiction.
*/
public class EvaluateDivision {

    public double[] calcEquation(final List<List<String>> equations,
                                 final double[] values,
                                 final List<List<String>> queries) {
        final Map<String, Map<String, Double>> neib = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            neib.putIfAbsent(equations.get(i).get(0), new HashMap<>());
            neib.putIfAbsent(equations.get(i).get(1), new HashMap<>());
            neib.get(equations.get(i).get(0)).put(equations.get(i).get(1), values[i]);
            neib.get(equations.get(i).get(1)).put(equations.get(i).get(0), 1 / values[i]);
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            res[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), neib, new HashSet<>());
        }
        return res;
    }

    private double dfs(final String curr,
                       final String target,
                       final Map<String, Map<String, Double>> neib,
                       final HashSet<String> visited) {
        if (!neib.containsKey(curr))
            return -1;
        visited.add(curr);
        if (curr.equals(target))
            return 1;
        for (final Map.Entry<String, Double> entry : neib.get(curr).entrySet()) {
            if (visited.contains(entry.getKey())) continue;
            final double res = dfs(entry.getKey(), target, neib, visited);
            if (res > 0)
                return res * entry.getValue();
        }
        return -1;
    }

}
