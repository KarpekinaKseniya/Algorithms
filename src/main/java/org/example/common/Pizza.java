package org.example.common;

/*
    Given a rectangular pizza represented as a rows x cols matrix containing
    the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k.
    You have to cut the pizza into k pieces using k-1 cuts.

    For each cut you choose the direction: vertical or horizontal, then you choose a cut position
    at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give
    the left part of the pizza to a person. If you cut the pizza horizontally, give the upper
    part of the pizza to a person. Give the last piece of pizza to the last person.

    Return the number of ways of cutting the pizza such that each piece contains at least one apple.
    Since the answer can be a huge number, return this modulo 10^9 + 7.
 */
public class Pizza {

    public int ways(final String[] pizza, final int k) {
        int MOD = 1_000_000_007;
        final int row = pizza.length;
        int column = pizza[0].length();
        int[][] appleCounts = new int[row][column];
        int[][][] waysOfCutting = new int[row][column][k + 1];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = column - 1; j >= 0; j--) {
                int appleInCurrentCell = pizza[i].charAt(j) == 'A' ? 1 : 0;
                if (i != row - 1 && j != column - 1) {
                    appleCounts[i][j] = appleInCurrentCell + appleCounts[i + 1][j] + appleCounts[i][j + 1] - appleCounts[i + 1][j + 1];
                } else if (i == row - 1 && j == column - 1) {
                    appleCounts[i][j] = appleInCurrentCell;
                } else if (i == row - 1 && j != column - 1) {
                    appleCounts[i][j] = appleInCurrentCell + appleCounts[i][j + 1];
                } else {
                    appleCounts[i][j] = appleInCurrentCell + appleCounts[i + 1][j];
                }
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            for (int j = column - 1; j >= 0; j--) {
                for (int piece = 1; piece <= k; piece++) {
                    if (appleCounts[i][j] == 0) {
                        waysOfCutting[i][j][piece] = 0;
                        continue;
                    }
                    if (piece == 1) {
                        waysOfCutting[i][j][piece] = 1;
                        continue;
                    }
                    for (int cut = i + 1; cut < row; cut++) {
                        if (appleCounts[i][j] - appleCounts[cut][j] > 0) {
                            waysOfCutting[i][j][piece] += waysOfCutting[cut][j][piece - 1];
                            waysOfCutting[i][j][piece] %= MOD;
                        }
                    }
                    for (int cut = j + 1; cut < column; cut++) {
                        if (appleCounts[i][j] - appleCounts[i][cut] > 0) {
                            waysOfCutting[i][j][piece] += waysOfCutting[i][cut][piece - 1];
                            waysOfCutting[i][j][piece] %= MOD;
                        }
                    }
                }
            }
        }
        return waysOfCutting[0][0][k];
    }
}
