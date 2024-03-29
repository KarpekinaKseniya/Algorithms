package org.example.common;

/*
    The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
    (you may want to display this pattern in a fixed font for better legibility)
        P   A   H   N
        A P L S I I G
        Y   I   R
    And then read line by line: "PAHNAPLSIIGYIR"
*/
public class ZigzagConversion {

    public String convert(final String s, final int numRows) {
        final char[] c = s.toCharArray();
        final int len = c.length;
        final StringBuffer[] sb = new StringBuffer[numRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuffer();
        }
        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < numRows && i < len; idx++) {
                sb[idx].append(c[i++]);
            }
            for (int idx = numRows - 2; idx >= 1 && i < len; idx--) {
                sb[idx].append(c[i++]);
            }
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
    }
}
