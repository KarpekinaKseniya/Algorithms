package org.example.common;

import java.util.ArrayList;
import java.util.List;

/*
    Given an integer rowIndex, return the rowIndex-th (0-indexed) row of the Pascal's triangle.
*/
public class PascalTriangle {
    public List<Integer> getRow(final int rowIndex) {
        final List<Integer> result = new ArrayList<>();
        result.add(1);
        if (rowIndex == 0) {
            return result;
        }
        final List<Integer> preview = getRow(rowIndex - 1);
        for (int i = 1; i < preview.size(); i++) {
            final int curr = preview.get(i - 1) + preview.get(i);
            result.add(curr);
        }
        result.add(1);
        return result;
    }

    /*
        Given an integer numRows, return the first numRows of Pascal's triangle.
        In Pascal's triangle, each number is the sum of the two numbers directly above it.
    */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> level = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                int toAdd = 1;
                if (j != 0 && j != i) {
                    toAdd = result.get(i - 1).get(j - 1) + result.get(i - 1).get(j);
                }
                level.add(toAdd);
            }
            result.add(level);
        }
        return result;
    }
}
