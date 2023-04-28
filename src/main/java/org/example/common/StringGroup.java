package org.example.common;

/*
    Two strings X and Y are similar if we can swap two letters (in different positions) of X,
    so that it equals Y. Also two strings X and Y are similar if they are equal.

    For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts"
    are similar, but "star" is not similar to "tars", "rats", or "arts".

    Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.
    Notice that "tars" and "arts" are in the same group even though they are not similar.
    Formally, each group is such that a word is in the group if and only if it is similar to at
    least one other word in the group.

    We are given a list strs of strings where every string in strs is an anagram of every other
    string in strs. How many groups are there?
*/
public class StringGroup {

    public int numSimilarGroups(String[] strs) {
        final int len = strs.length;
        int[] par = new int[len];
        int groups = len;
        for (int i = 0; i < len; i++) {
            par[i] = i;
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                final boolean similar = isSimilar(strs, i, j);
                if (similar) {
                    int p1 = findPar(par, i);
                    int p2 = findPar(par, j);
                    if (p1 != p2) {
                        par[p1] = p2;
                        groups--;
                    }
                }
            }
        }
        return groups;
    }

    private int findPar(final int[] par, final int u) {
        return par[u] == u ? u : (par[u] = findPar(par, par[u]));
    }

    private boolean isSimilar(final String[] group, final int i, final int j) {
        int count = 0;
        for (int k = 0; k < group[i].length(); k++) {
            char ch1 = group[i].charAt(k);
            char ch2 = group[j].charAt(k);
            if (ch1 != ch2) {
                count++;
            }
            if (count > 2) {
                return false;
            }
        }
        return true;
    }

}
