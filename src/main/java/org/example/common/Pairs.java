package org.example.common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/*
    You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
    Define a pair (u, v) which consists of one element from the first array and one element
    from the second array.
    Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
*/
public class Pairs {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new customComparator());
        HashSet<String> set = new HashSet<>();
        int[] tem = new int[3];
        int sum = nums1[0] + nums2[0];
        tem[0] = sum;
        tem[1] = 0;
        tem[2] = 0;
        set.add("0$0");
        pq.add(tem);
        while (pq.size() > 0 && k > 0) {
            int[] tem2 = pq.poll();
            int i = tem2[1];
            int j = tem2[2];
            List<Integer> ls = new ArrayList<>();
            ls.add(nums1[i]);
            ls.add(nums2[j]);
            ans.add(ls);
            String s1 = i + "$" + (j + 1);
            String s2 = (i + 1) + "$" + j;
            if (j + 1 < nums2.length && !set.contains(s1)) {
                int[] tem1 = new int[3];
                sum = nums1[i] + nums2[j + 1];
                tem1[0] = sum;
                tem1[1] = i;
                tem1[2] = j + 1;
                pq.add(tem1);
                set.add(s1);
            }
            if (i + 1 < nums1.length && !set.contains(s2)) {
                int[] tem1 = new int[3];
                sum = nums1[i + 1] + nums2[j];
                tem1[0] = sum;
                tem1[1] = i + 1;
                tem1[2] = j;
                pq.add(tem1);
                set.add(s2);
            }
            k--;
        }

        return ans;
    }


    // sort the data in ascending order
    class customComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return a[0] - b[0];
        }
    }
}
