package org.example.common;

public class Smoother {

    /*
        An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by
        rounding down the average of the cell and the eight surrounding cells (i.e., the average of
        the nine cells in the blue smoother). If one or more of the surrounding cells of a cell is not
        present, we do not consider it in the average (i.e., the average of the four cells in the red
        smoother).
        Given an m x n integer matrix img representing the grayscale of an image, return the image after
        applying the smoother on each cell of it.
    */
    public int[][] imageSmoother(int[][] img) {
        var moves = new int[]{-1, 0, 1};
        var m = img.length;
        var n = img[0].length;
        var res = new int[m][n];
        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                var cnt = 0;
                var val = 0;
                for (var a : moves) {
                    for (var b : moves) {
                        var x = i + a;
                        var y = j + b;
                        if (x < 0 || x >= m || y < 0 || y >= n) continue;
                        cnt++;
                        val += img[x][y];
                    }
                }
                res[i][j] = val / cnt;
            }
        }
        return res;
    }

}
