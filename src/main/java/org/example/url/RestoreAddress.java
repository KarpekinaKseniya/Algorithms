package org.example.url;

import java.util.ArrayList;
import java.util.List;

/*
    A valid IP address consists of exactly four integers separated by single dots.
    Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
    For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245",
    "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
    Given a string s containing only digits, return all possible valid IP addresses
    that can be formed by inserting dots into s.
    You are not allowed to reorder or remove any digits in s.
    You may return the valid IP addresses in any order.
*/
public class RestoreAddress {

    public List<String> restoreIpAddresses(final String s) {
        return findIpAddresses(s, 0);
    }

    private boolean isValid(final String s) {
        if (s.length() > 3 || s.length() == 0)
            return false;
        if (s.length() > 1 && s.charAt(0) == '0')
            return false;
        return s.length() != 3 || Integer.parseInt(s) <= 255;
    }

    private List<String> findIpAddresses(final String s, final int idx) {
        final List<String> ret = new ArrayList<>();
        if (idx == 3) {
            if (isValid(s)) {
                ret.add(s);
            }
            return ret;
        }
        final int len = s.length();
        for (int i = 1; i <= 3; ++i) {
            if (i <= len) {
                final String a = s.substring(0, i);
                if (!isValid(a)) {
                    continue;
                }
                List<String> l1 = findIpAddresses(s.substring(i), idx + 1);
                for (String str : l1) {
                    ret.add(a + "." + str);
                }
            }
        }
        return ret;
    }
}
