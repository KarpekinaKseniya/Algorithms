package org.example.common;

/*
    Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
*/
public class Excel {
    private static final char ASCII_OFFSET = 64;

    public String convertToTitle(int columnNumber) {
        int div = columnNumber;
        StringBuilder title = new StringBuilder();

        while (div > 0) {
            if (div <= 26) {
                title.insert(0, (char) (div + ASCII_OFFSET));
                div = 0;
            } else {
                int mod = div % 26;
                title.insert(0, mod > 0 ? (char) (mod + ASCII_OFFSET) : 'Z');
                div = div / 26 - (mod == 0 ? 1 : 0);
            }
        }

        return title.toString();
    }
}
