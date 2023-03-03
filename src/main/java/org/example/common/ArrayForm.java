package org.example.common;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
    The array-form of an integer num is an array representing its digits in left to right order.
    For example, for num = 1321, the array form is [1,3,2,1].
    Given num, the array-form of an integer, and an integer k,
    return the array-form of the integer num + k.
*/
public class ArrayForm {

    public List<Integer> addToArrayForm(final int[] num, final int k) {
        final List<Integer> list = new LinkedList<>();
        int added = k;
        int carry = 0;
        for (int i = num.length - 1; i >= 0; i--) {
            int num1 = 0;
            if (added != 0) {
                num1 = added % 10;
                added /= 10;
            }
            final int num2 = num[i];
            final int sum = carry + num1 + num2;
            carry = sum / 10;
            list.add(sum % 10);
        }
        while (added != 0) {
            final int sum = carry + added % 10;
            added /= 10;
            carry = sum / 10;
            list.add(sum % 10);
        }
        if (carry != 0) {
            list.add(carry);
        }
        Collections.reverse(list);
        return list;
    }
}
