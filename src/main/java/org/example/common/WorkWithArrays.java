package org.example.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class WorkWithArrays {

    /*
    Given an array nums of size n, return the majority element.

    The majority element is the element that appears more than n / 2 times.
    You may assume that the majority element always exists in the array.
    */
    public int findMajorityElement(final int[] nums) {
        final int appears = nums.length / 2;
        final Map<Integer, Integer> map = new HashMap<>();
        for (final int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        //@formatter:off
    return map.entrySet().stream()
        .filter(value -> value.getValue() > appears)
        .map(Map.Entry::getKey)
        .findFirst().orElseThrow(() -> new RuntimeException("Majority element not found"));
    //@formatter:off
  }

  /*
  Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
  Return the array in the form [x1,y1,x2,y2,...,xn,yn].
  */
  public int[] shuffle(final int[] nums, final int n) {
    int[] result = new int[nums.length];
    int j = 0;
    for (int i = 0; i < n; i++) {
      result[j++] = nums[i];
      result[j++] = nums[i + n];
    }
    return result;
  }

  /*
      Given an array of integers nums sorted in non-decreasing order,
      find the starting and ending position of a given target value.
      If target is not found in the array, return [-1, -1].
      You must write an algorithm with O(log n) runtime complexity.
  */
  public int[] searchRange(final int[] nums, final int target) {
    int[] result = {-1, -1};
    final List<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      final int num = nums[i];
      if (num == target) {
        indexes.add(i);
      }
    }
    if (indexes.isEmpty()) {
      return result;
    }
    result[0] = indexes.get(0);
    if (indexes.size() > 1) {
      result[1] = indexes.get(indexes.size() - 1);
    } else {
      result[1] = result[0];
    }
    return result;
  }

  /*
      You are given a sorted array consisting of only integers where every element appears exactly twice,
      except for one element which appears exactly once.
      Return the single element that appears only once.
  */
  public int singleNonDuplicate(final int[] nums) {
    final Map<Integer, Integer> map = new HashMap<>();
    for (final int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    return map.entrySet().stream()
        .filter(value -> value.getValue() == 1)
        .map(Map.Entry::getKey)
        .findFirst().orElse(-1);
  }

  /*
      You are given an array nums of n positive integers.
      You can perform two types of operations on any element of the array any number of times:
      If the element is even, divide it by 2.
      For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
      If the element is odd, multiply it by 2.
      For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].
      The deviation of the array is the maximum difference between any two elements in the array.
      Return the minimum deviation the array can have after performing some number of operations.
  */
  public int minimumDeviation(final int[] nums) {
    final PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    int min = Integer.MAX_VALUE;
    for (int i : nums) {
      if (i % 2 == 1) {
        i *= 2;
      }

      pq.add(i);
      min = Math.min(min, i);
    }
    int diff = Integer.MAX_VALUE;
    while (pq.peek() % 2 == 0) {
      int max = pq.remove();
      diff = Math.min(diff, max - min);

      max /= 2;
      min = Math.min(min, max);
      pq.add(max);
    }
    return Math.min(diff, pq.peek() - min);
  }

  /*
      Given a 0-indexed integer array nums, find a 0-indexed integer array answer where:
          answer.length == nums.length.
          answer[i] = |leftSum[i] - rightSum[i]|.
      Where:
          leftSum[i] is the sum of elements to the left of the index i in the array nums.
          If there is no such element, leftSum[i] = 0.
          rightSum[i] is the sum of elements to the right of the index i in the array nums.
          If there is no such element, rightSum[i] = 0.
      Return the array answer.
  */
  public int[] leftRightDifference(final int[] nums) {
    int leftSum = 0, rightSum = 0, len = nums.length;
    for (final int num : nums) {
      rightSum += num;
    }
    for (int i = 0; i < len; i++) {
      int val = nums[i];
      rightSum -= val;
      nums[i] = Math.abs(leftSum - rightSum);
      leftSum += val;
    }
    return nums;
  }

  /*
      You are given an integer array nums and two integers minK and maxK.
      A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
          The minimum value in the subarray is equal to minK.
          The maximum value in the subarray is equal to maxK.
          Return the number of fixed-bound subarrays.
      A subarray is a contiguous part of an array.
  */
  public long countSubarrays(final int[] nums, final int minK, final int maxK) {
    int minI = -1, maxI = -1, left = -1, right = 0;
    long count = 0;
    while (right < nums.length) {
      if (nums[right] < minK || nums[right] > maxK) {
        minI = right;
        maxI = right;
        left = right;
      }
      minI = nums[right] == minK ? right : minI;
      maxI = nums[right] == maxK ? right : maxI;
      count += Math.min(minI, maxI) - left;
      right++;
    }
    return count;
  }

  /*
      Given an array arr of positive integers sorted in a strictly increasing order,
      and an integer k.
      Return the kth positive integer that is missing from this array.
  */
  public int findKthPositive(final int[] arr, final int k) {
    int lo = 0;
    int hi = arr.length;
    while (lo < hi) {
      final int mid = lo + (hi - lo) / 2;
      final int missing = arr[mid] - (mid + 1);
      if (missing < k) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }
    return lo + k;
  }

  /*
      You are given a 0-indexed array nums comprising of n non-negative integers.
      In one operation, you must:
          Choose an integer i such that 1 <= i < n and nums[i] > 0.
          Decrease nums[i] by 1.
          Increase nums[i - 1] by 1.
      Return the minimum possible value of the maximum integer of nums after performing
          any number of operations.
  */
  public int minimizeArrayValue(final int[] nums) {
    int sum = 0;
    int ans = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      ans = Math.max(ans, (sum + i) / (i + 1));
    }
    return ans;
  }

  /*
      You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
      A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
      Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
  */
  public int numEnclaves(final int[][] grid) {
    final int m = grid.length;
    final int n = grid[0].length;
    int[][] visited = new int[m][n];
    for (int i = 0; i < m; i++) {
      visited[i] = grid[i].clone();
    }
    final Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && (visited[i][j] == 1)) {
          q.offer(new int[]{i, j});
          visited[i][j] = 0;
        }
      }
    }
    final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    while (!q.isEmpty()) {
      final int[] cell = q.poll();
      final int x = cell[0];
      final int y = cell[1];
      for (final int[] dir : dirs) {
        final int i = x + dir[0];
        final int j = y + dir[1];
        if (i >= 0 && j >= 0 && i < m && j < n && visited[i][j] == 1) {
          q.offer(new int[]{i, j});
          visited[i][j] = 0;
        }
      }
    }
    int countLands = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (visited[i][j] == 1) {
          countLands++;
        }
      }
    }
    return countLands;
  }

  /*
      Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
        answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
        answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
      Note that the integers in the lists may be returned in any order.
  */
  public List<List<Integer>> findDifference(final int[] nums1, final int[] nums2) {
    final Set<Integer> set1 = new HashSet<>();
    final Set<Integer> set2 = new HashSet<>();
    for(final int ele : nums1) {
      set1.add(ele);
    }
    for(final int ele : nums2) {
      set2.add(ele);
    }
    final List<List<Integer>> list = new ArrayList<>();
    final List<Integer> l1 = new ArrayList<>();
    final List<Integer> l2 = new ArrayList<>();
    for(final int ele : set2) {
      if(!set1.contains(ele)) {
        l1.add(ele);
      }
    }
    for(final int ele : set1) {
      if(!set2.contains(ele)) {
        l2.add(ele);
      }
    }
    list.add(l2);
    list.add(l1);
    return list;
  }

  /*
  Given an array of integers nums, sort the array in ascending order and return it.
  You must solve the problem without using any built-in functions in O(nlog(n))
  time complexity and with the smallest space complexity possible.
   */
  public int[] sortArray(final int[] nums) {
    quickSort(nums, 0, nums.length - 1);
    return nums;
  }

  private void quickSort(final int[] nums, final int s, final int e) {
    if (s >= e) {
      return;
    }
    int p = nums[s];
    int curr = s + 1, back = e;
    while (curr <= back) {
      if (p > nums[curr]) {
        curr++;
      } else if (p > nums[back]) {
        swap(nums, curr, back);
        back--;
      } else {
        back--;
      }
    }
    swap(nums, s, curr - 1);
    quickSort(nums, s, curr - 1);
    quickSort(nums, curr, e);
  }

  private void swap(final int[] nums, final int i, final int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }

  /*
    Given a string s and an integer k, return the maximum number of vowel letters in any
    substring of s with length k.
    Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
  */
  public int maxVowels(final String s, final int k) {
    int max = 0;
    if(s.length() == 0)
      return max;
    int secMax=0;
    for(int i = 0; i < k; i++){
      char ch = s.charAt(i);
      if(isVowel(ch)){
        secMax++;
      }
    }
    max = Math.max(max,secMax);
    for(int i = k; i < s.length(); i++){
      final char c = s.charAt(i - k);
      if(isVowel(c)){
        secMax--;
      }
      if(isVowel(s.charAt(i))){
        secMax++;
      }
      max = Math.max(max,secMax);
    }
    return max;
  }
  private boolean isVowel(final char c){
    return c =='a' || c =='i' || c =='e' || c =='o' || c=='u';
  }

  /*
      A sequence of numbers is called an arithmetic progression if the difference
      between any two consecutive elements is the same.
      Given an array of numbers arr, return true if the array can be rearranged
      to form an arithmetic progression. Otherwise, return false.
  */
  public boolean canMakeArithmeticProgression(final int[] arr) {
    Arrays.sort(arr);
    final int diff = arr[0] - arr[1];
    for (int i = 1; i < arr.length - 1; i++) {
      if (diff != arr[i] - arr[i + 1]) {
        return false;
      }
    }
    return true;
  }

  /*
    Given a binary array nums,
    you should delete one element from it.
    Return the size of the longest non-empty subarray containing only 1's in the resulting array.
    Return 0 if there is no such subarray.
  */
  public int longestSubarray(int[] nums) {
    int result = 0;
    int z = 0, si = 0 , cur = 0, ei = nums.length - 1;
    while(cur <= ei) {
      if(nums[cur] == 0) {
        z++;
      }
      while(si <= cur && z > 1 ) {
        if(nums[si] == 0) {
          z--;
        }
        si++;
      }
      result = Math.max(result, cur - si);
      cur++;
    }
    return result;
  }

  /*
    Given an array of positive integers nums and a positive integer target,
    return the minimal length of a subarray whose sum is greater than or
    equal to target. If there is no such subarray, return 0 instead.
  */
  public int minSubArrayLen(int target, int[] nums) {
    int i = 0;
    int j = 0;
    int min = Integer.MAX_VALUE;
    int sum = 0;
    while(j < nums.length){
      sum += nums[j];
      if(sum >= target){
        while(sum >= target){
          min = Math.min(min,j - i + 1);
          sum -= nums[i];
          i++;
        }
      }
      j++;
    }
    return min == Integer.MAX_VALUE ? 0 : min;
  }
}
