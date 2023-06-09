package org.example.common;

public class Letter {

    /*
        You are given an array of characters letters that is sorted in non-decreasing order,
        and a character target. There are at least two different characters in letters.
        Return the smallest character in letters that is lexicographically greater than target.
        If such a character does not exist, return the first character in letters.
    */
    public char nextGreatestLetter(final char[] letters, final char target) {
        for (final char letter : letters)
            if (letter > target)
                return letter;
        return letters[0];
    }

}
