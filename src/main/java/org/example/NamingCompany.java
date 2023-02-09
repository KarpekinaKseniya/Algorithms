package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
    You are given an array of strings ideas that represents
    a list of names to be used in the process of naming a company.
    The process of naming a company is as follows:
    Choose 2 distinct names from ideas, call them ideaA and ideaB.
    Swap the first letters of ideaA and ideaB with each other.
    If both of the new names are not found in the original ideas,
    then the name ideaA ideaB (the concatenation of ideaA and ideaB,
    separated by a space) is a valid company name.
    Otherwise, it is not a valid name.
    Return the number of distinct valid names for the company.
*/
public class NamingCompany {

    public long distinctNames(final String[] ideas) {
        final Map<Integer, HashSet<String>> group = new HashMap<>();
        for (final String idea : ideas) {
            final int firstChar = idea.charAt(0) - 'a';
            if (!group.containsKey(firstChar)) {
                group.put(firstChar, new HashSet<>());
            }
            group.get(firstChar).add(idea.substring(1));
        }
        long count = 0;
        for (int a = 0; a < 25; a++) {
            for (int b = a + 1; b < 26; b++) {
                int len = 0;
                if (group.get(a) != null && group.get(b) != null) {
                    for (final String word : group.get(a)) {
                        if (group.get(b).contains(word)) {
                            len++;
                        }
                    }
                    final int calc = 2 * ((group.get(a).size() - len) * (group.get(b).size() - len));
                    count += calc;
                }

            }
        }
        return count;
    }
}
