package org.example;

/*
    Check if a given number is an Armstrong number
 */
class ArmstrongNumber {

    public boolean isArmstrongNumber(final int number) {
        final String temp = Integer.toString(number);
        final int len = temp.length();
        final int[] digits = new int[len];
        for (int i = 0; i < len; i++) {
            digits[i] = temp.charAt(i) - '0';
        }
        int result = 0;
        for (final int digit : digits) {
            result += Math.round(Math.pow(digit, len));
        }
        return number == result;
    }
}
