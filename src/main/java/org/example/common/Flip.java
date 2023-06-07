package org.example.common;

/*
    Given 3 positives numbers a, b and c.
    Return the minimum flips required in some bits of a and b to make ( a OR b == c ).
    Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in
    their binary representation.
*/
public class Flip {

    public int minFlips(int a, int b, int c) {
        int cnt = 0;
        while (a > 0 || b > 0 || c > 0) {
            if ((c & 1) == 0) {
                if ((a & 1) == 1) {
                    cnt++;
                }
                if ((b & 1) == 1) {
                    cnt++;
                }
            } else if ((a & 1) == 0 && (b & 1) == 0) {
                cnt++;
            }
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
        return cnt;
    }

}
