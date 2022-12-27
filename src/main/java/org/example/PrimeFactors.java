package org.example;

import java.util.ArrayList;
import java.util.List;

/*
    Find all prime factors of a given number
 */
class PrimeFactors {

    public int[] findAllPrimeFactors(final int n) {
        int number = n;
        final List<Integer> primes = new ArrayList<>();
        while (number % 2 == 0) {
            primes.add(2);
            number /= 2;
        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            while (number % i == 0) {
                primes.add(i);
                number /= i;
            }
        }
        if (number > 2) {
            primes.add(number);
        }
        return primes.stream().mapToInt(Integer::intValue).toArray();
    }
}
