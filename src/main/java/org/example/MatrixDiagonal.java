package org.example;

/*
Given a square matrix mat, return the sum of the matrix diagonals.
Only include the sum of all the elements on the primary diagonal and all the elements on the
secondary diagonal that are not part of the primary diagonal.
 */
public class MatrixDiagonal {

    public int diagonalSum(int[][] matrix) {
        int sum = 0;
        if (!isValidSquareMatrix(matrix)) {
            throw new IllegalArgumentException("Input matrix is not valid");
        }
        final int len = matrix.length;
        for (int i = 0; i < len; i++) {
            final int secondDiagonal = len - i - 1;
            sum += matrix[i][i];
            if (secondDiagonal != i) {
                sum += matrix[i][secondDiagonal];
            }
        }
        return sum;
    }

    private boolean isValidSquareMatrix(int[][] matrix) {
        final int rows = matrix.length;
        for (int[] arr : matrix) {
            if (rows != arr.length) {
                return false;
            }
        }
        return true;
    }
}
