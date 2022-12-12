package org.example;

/*
    You are given a string s, where every two consecutive vertical bars '|' are grouped into a pair.
    In other words, the 1st and 2nd '|' make a pair, the 3rd and 4th '|' make a pair, and so forth.

    Return the number of '*' in s, excluding the '*' between each pair of '|'.

    Note that each '|' will belong to exactly one pair.
*/
public class Asterisks {

    public int countAsterisks(final String s) {
        int count = 0;
        int res = 0;
        for (final char ch : s.toCharArray()) {
            if (ch == '*')
                if (count % 2 == 0)
                    res++;
            if (ch == '|')
                count++;
        }
        return res;
    }
}
