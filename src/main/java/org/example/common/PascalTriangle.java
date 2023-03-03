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
}
