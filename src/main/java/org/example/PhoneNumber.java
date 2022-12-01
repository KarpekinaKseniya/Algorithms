package org.example;

/*
    You are given a phone number as a string number. number consists of digits, spaces ' ', and/or dashes '-'.
    You would like to reformat the phone number in a certain manner. Firstly, remove all spaces and dashes.
    Then, group the digits from left to right into blocks of length 3 until there are 4 or fewer digits. The final digits are then grouped as follows:
        2 digits: A single block of length 2.
        3 digits: A single block of length 3.
        4 digits: Two blocks of length 2 each.
    The blocks are then joined by dashes.
    Notice that the reformatting process should never produce any blocks of length 1 and produce at most two blocks of length 2.
    Return the phone number after formatting.
*/
class PhoneNumber {

    private static final String EMPTY_STRING = "";
    private static final String NOT_NUMBER_REGEX = "\\D";

    public String reformatNumber(final String number) {
        String numberOnly = number.replaceAll(NOT_NUMBER_REGEX, EMPTY_STRING);
        int len = numberOnly.length();
        if (len <= 3)
            return numberOnly;
        StringBuilder sb = new StringBuilder();
        while (len > 0) {
            if (len == 4) {
                sb.append(numberOnly, 0, 2).append("-").append(numberOnly.substring(2));
                break;
            } else if (len >= 3) {
                sb.append(numberOnly, 0, 3);
                numberOnly = numberOnly.substring(3);
                len -= 3;
                if (len != 0)
                    sb.append("-");
            } else {
                sb.append(numberOnly);
                break;
            }
        }
        return sb.toString();
    }
}
