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
import java.util.Stack;
import java.util.stream.Collectors;

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

  /*
      A teacher is writing a test with n true/false questions,
      with 'T' denoting true and 'F' denoting false. He wants to confuse the students by
      maximizing the number of consecutive questions with the same answer (multiple trues or multiple
      falses in a row).
      You are given a string answerKey, where answerKey[i] is the original answer to the ith question.
      In addition, you are given an integer k, the maximum number of times you may perform
      the following operation:
      Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
      Return the maximum number of consecutive 'T's or 'F's in the answer key after performing
      the operation at most k times.
  */
  public int maxConsecutiveAnswers(String answerKey, int k) {
    int result = 0;
    int max_count = 0;
    int[] count = new int[2];
    for(int l = 0,r=0;r<answerKey.length();r++)
    {
      max_count = Math.max(max_count,++count[answerKey.charAt(r) == 'T' ? 1: 0]);
      while(max_count + k < r-l+1)
      {
        --count[answerKey.charAt(l++) == 'T'? 1 : 0];
      }
      result = Math.max(result,r-l+1);
    }
    return result;
  }

  /*
    An array arr a mountain if the following properties hold:
      arr.length >= 3
      There exists some i with 0 < i < arr.length - 1 such that:
        arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
        arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
    Given a mountain array arr, return the index i such that
    arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
    You must solve it in O(log(arr.length)) time complexity.
  */
  public int peakIndexInMountainArray(int[] arr) {
    int count = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] < arr[i + 1]) {
        count++;
      }
    }
    return count;
  }

  /*
      There is an integer array nums sorted in ascending order (with distinct values).
      Prior to being passed to your function, nums is possibly rotated at an unknown pivot
      index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ...,
      nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might
      be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
      Given the array nums after the possible rotation and an integer target, return the index
      of target if it is in nums, or -1 if it is not in nums.
      You must write an algorithm with O(log n) runtime complexity.
  */
  public int search(int[] nums, int target) {
    int index = -1;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == target) {
        index = i;
        break;
      }
    }
    return index;
  }

  /*
      You are given a 0-indexed integer array nums and an integer p.
      Find p pairs of indices of nums such that the maximum difference amongst all the pairs
      is minimized. Also, ensure no index appears more than once amongst the p pairs.
      Note that for a pair of elements at the index i and j, the difference of this pair
      is |nums[i] - nums[j]|, where |x| represents the absolute value of x.
      Return the minimum maximum difference among all p pairs. We define the maximum
      of an empty set to be zero.
  */
  public int minimizeMax(int[] nums, int p) {
    if(nums.length == 1) {
      return 0;
    }
    int ans = 0;
    Arrays.sort(nums);
    int low = 0,high = nums[nums.length - 1] - nums[0];
    while(low <= high) {
      int mid = (low + high) / 2;
      int i = 1;
      int count = 0;
      while(i < nums.length) {
        if(nums[i] - nums[i - 1] <= mid) {
          count++;
          i = i+2;
        } else {
          i++;
        }
      }
      if(count >= p) {
        ans = mid;
        high = mid - 1;
      }
      else {
        low = mid + 1;
      }
    }
    return ans;
  }

  /*
    There is an integer array nums sorted in non-decreasing
    order (not necessarily with distinct values).
  Before being passed to your function, nums is rotated at an unknown pivot
  index k (0 <= k < nums.length) such that the resulting array
  is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
  For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
  Given the array nums after the rotation and an integer target, return true if target
  is in nums, or false if it is not in nums.
  You must decrease the overall operation steps as much as possible.
  */
  public boolean rotateSearch(int[] nums, int target) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++)
      if (nums[i] == target)
        return true;
    return false;
  }

  /*
      You are given an integer array coins representing coins of different denominations
      and an integer amount representing a total amount of money.
      Return the number of combinations that make up that amount. If that amount of money
      cannot be made up by any combination of the coins, return 0.
      You may assume that you have an infinite number of each kind of coin.
      The answer is guaranteed to fit into a signed 32-bit integer.
  */
  public int change(int amount, int[] coins) {
    int arr[] = new int[amount+1];
    arr[0] = 1;
    for(final int coin : coins)
      for(int i = coin; i <= amount; i++)
        arr[i] += arr[i-coin];
    return arr[amount];
  }

  /*
      Given an integer array nums and an integer k, return the k-th largest element in the array.
      Note that it is the k-th largest element in the sorted order, not the k-th distinct element.
      Can you solve it without sorting?
   */
  public int findKthLargest(int[] nums, int k) {
    final PriorityQueue<Integer> p = new PriorityQueue<>();
    for (final int x : nums) {
      p.offer(x);
      if (p.size() > k) {
        p.poll();
      }
    }
    return p.peek();
  }

  /*
      You are given a 0-indexed integer array nums. In one operation you can replace any element
      of the array with any two elements that sum to it.
      For example, consider nums = [5,6,7]. In one operation, we can replace nums[1] with 2 and 4 and
      convert nums to [5,2,4,7].
      Return the minimum number of operations to make an array that is sorted in non-decreasing order.
   */
  public long minimumReplacement(int[] nums) {
     long result = 0;
     int n = nums.length;
     int prev = nums[n - 1];
     for(int i = n - 2; i >= 0; i--){
         if(prev >= nums[i]){
             prev = nums[i];
         }
         else{
             int parts = (int)Math.ceil((double)nums[i] / prev) - 1;
             result += parts;
             prev = Math.min(prev, nums[i] / (parts + 1));
            }
     }
     return result;
    }

    /*
    There is a one-dimensional garden on the x-axis.
    The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).
    There are n + 1 taps located at points [0, 1, ..., n] in the garden.
    Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can
    water the area [i - ranges[i], i + ranges[i]] if it was open.
    Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.
     */
  public int minTaps(int n, int[] ranges) {
    int[] maxReach = new int[n + 1];

    for(int i = 0; i < ranges.length; i++) {
      int s = Math.max(0, i - ranges[i]), e = i + ranges[i];
      maxReach[s] = e;
    }

    int tap = 0, currEnd = 0, nextEnd = 0;
    for(int i = 0; i <= n; i++) {
      if(i > nextEnd) return -1;
      if(i > currEnd) {
        tap++;
        currEnd = nextEnd;
      }
      nextEnd = Math.max(nextEnd, maxReach[i]);
    }
    return tap;
  }

  /*
      Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of
      the two sorted arrays.
      The overall run time complexity should be O(log (m+n)).
  */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int len = nums1.length + nums2.length;
    List<Integer> list = new ArrayList<>(len);
    List<Integer> listOfnums1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
    List<Integer> listOfnums2 = Arrays.stream(nums2).boxed().collect(Collectors.toList());
    list.addAll(listOfnums1);
    list.addAll(listOfnums2);
    Collections.sort(list);
    int avarageElem = (int)Math.floor(len/2);
    if (len == 1) return list.get(0);
    return ((len % 2 == 0) ?
            ((list.get(avarageElem) + list.get(avarageElem - 1)) / (double)2) :
            list.get(avarageElem));
  }

  /*
    Given an integer array nums, move all the even integers at the beginning of the array
    followed by all the odd integers.
    Return any array that satisfies this condition.
  */
  public int[] sortArrayByParity(int[] nums) {
    final int len = nums.length;
    final List<Integer> result = new ArrayList<>(len);
    for(final int value : nums){
      if (value % 2 == 0) {
        result.add(0, value);
      } else {
        result.add(value);
      }
    }
    return result.stream().mapToInt(i -> i).toArray();
  }

  /*
      An array is monotonic if it is either monotone increasing or monotone decreasing.
      An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j].
      An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].
      Given an integer array nums, return true if the given array is monotonic, or false otherwise.
  */
  public boolean isMonotonic(int[] nums) {
    boolean increasing = true;
    boolean decreasing = true;

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > nums[i - 1]) {
        decreasing = false;
      } else if (nums[i] < nums[i - 1]) {
        increasing = false;
      }
    }
    return increasing || decreasing;
  }

  /*
      Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i],
      nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
      Return true if there is a 132 pattern in nums, otherwise, return false.
  */
  public boolean find132pattern(int[] nums) {
    if(nums == null || nums.length == 0) return false;
    Stack<Integer> stack = new Stack<>();
    int s3 = Integer.MIN_VALUE;
    for(int i = nums.length - 1; i >= 0; i--){
      if(nums[i] < s3) return true;
      else while(!stack.isEmpty() && nums[i] > stack.peek()) s3 = stack.pop();
      stack.push(nums[i]);
    }
    return false;
  }
}
