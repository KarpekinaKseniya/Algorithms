package org.example;

/*
    Given a string s, find the length of the longest substring without repeating characters.
 */
class LongestSubstring {

    public int findLengthOfLongestSubstring(final String s) {
        if (s == null) {
            return -1;
        }
        final int length = s.length();
        if (length <= 1) {
            return length;
        }
        int maxLength = 0;
        StringBuilder sub = new StringBuilder();
        for (int i = 0; i < length; i++) {
            final char value = s.charAt(i);
            if (sub.toString().contains(String.valueOf(value))) {
                sub = new StringBuilder(sub.substring(sub.toString().indexOf(value) + 1));
            }
            sub.append(value);
            maxLength = Math.max(sub.length(), maxLength);
        }
        return maxLength;
    }

}
