package org.example.common;

/*
    The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
        countAndSay(1) = "1"
        countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1),
            which is then converted into a different digit string.
    To determine how you "say" a digit string,
    split it into the minimal number of substrings such that each substring contains exactly one unique digit.
    Then for each substring, say the number of digits, then say the digit. Finally, concatenate every said digit.
*/
public class CountAndSay {
    public String countAndSay(final int n) {
        if (n == 1) {
            return "1";
        }
        final String prev = countAndSay(n - 1);
        final StringBuilder result = new StringBuilder();
        int numDigit = 1;
        char currentNum = prev.charAt(0);
        for (int i = 1; i < prev.length(); i++) {
            if (prev.charAt(i) == currentNum) {
                numDigit++;
            } else {
                result.append(numDigit).append(currentNum);
                numDigit = 1;
                currentNum = prev.charAt(i);
            }
        }
        result.append(numDigit).append(currentNum);
        return result.toString();
    }
}
