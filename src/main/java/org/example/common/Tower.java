package org.example.common;

public class Tower {

    /*
        We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses,
        and so on until the 100th row.  Each glass holds one cup of champagne.
        Then, some champagne is poured into the first glass at the top.
         When the topmost glass is full, any excess liquid poured will fall equally to the glass
         immediately to the left and right of it.  When those glasses become full, any excess champagne
         will fall equally to the left and right of those glasses, and so on.  (A glass at the bottom
         row has its excess champagne fall on the floor.)
    */
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] result = new double[101][101];
        result[0][0] = poured;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <= i; j++) {
                if (result[i][j] >= 1) {
                    result[i + 1][j] += (result[i][j] - 1) / 2.0;
                    result[i + 1][j + 1] += (result[i][j] - 1) / 2.0;
                    result[i][j] = 1;
                }
            }
        }
        return result[query_row][query_glass];
    }

}
