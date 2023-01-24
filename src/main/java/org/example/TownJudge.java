package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
    In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.
    If the town judge exists, then:
        The town judge trusts nobody.
        Everybody (except for the town judge) trusts the town judge.
        There is exactly one person that satisfies properties 1 and 2.
    You are given an array trust where trust[i] = [a-i, b-i] representing that the person labeled a-i trusts the person labeled b-i.
    Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.
*/
public class TownJudge {

    private static final int WHO_TRUST_INDEX = 0;
    private static final int WHO_IS_TRUSTED_INDEX = 1;
    private static final int NOT_FOUND_VALUE = -1;

    public int findJudge(final int n, final int[][] trust) {
        if (n == 1) {
            return n;
        }
        final Map<Integer, Set<Integer>> trusts = new HashMap<>();
        for (final int[] ints : trust) {
            final int key = ints[WHO_IS_TRUSTED_INDEX];
            final Set<Integer> values = !trusts.containsKey(key) ? new HashSet<>() : trusts.get(key);
            values.add(ints[WHO_TRUST_INDEX]);
            trusts.put(key, values);
        }
        //@formatter:off
        final int jude = trusts.entrySet().stream()
                .filter(entry -> (n - 1) == entry.getValue().size())
                .map(Map.Entry::getKey)
                .findFirst().orElse(NOT_FOUND_VALUE);
        //@formatter:on
        if (jude == NOT_FOUND_VALUE) {
            return NOT_FOUND_VALUE;
        }
        for (final Set<Integer> values : trusts.values()) {
            if (values.contains(jude)) {
                return NOT_FOUND_VALUE;
            }
        }
        return jude;
    }
}
