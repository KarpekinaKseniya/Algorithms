package org.example.common;

/*
    You are given an integer array cookies, where cookies[i] denotes the number of cookies
    in the ith bag. You are also given an integer k that denotes the number of children
    to distribute all the bags of cookies to. All the cookies in the same bag must go
    to the same child and cannot be split up.
    The unfairness of a distribution is defined as the maximum total cookies obtained by
    a single child in the distribution.
    Return the minimum unfairness of all distributions.
*/
public class Cookies {

    int children[], ans = Integer.MAX_VALUE;

    public int distributeCookies(int[] cookies, int k) {
        children = new int[k];
        backTrack(cookies, 0);
        return ans;
    }

    private void backTrack(int[] cookies, int i) {
        for (var j = 0; j < children.length; j++) {
            children[j] += cookies[i];
            if (i == cookies.length - 1) {
                var max = 0;
                for (var a : children) max = Math.max(max, a);
                ans = Math.min(ans, max);
            } else {
                backTrack(cookies, i + 1);
            }
            children[j] -= cookies[i];
        }
    }

}
