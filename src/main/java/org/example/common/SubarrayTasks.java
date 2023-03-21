package org.example.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class SubarrayTasks {

  /*
  Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
  A circular array means the end of the array connects to the beginning of the array.
  Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
  A subarray may only include each element of the fixed buffer nums at most once.
  Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
  */
  public int maxSubarraySumCircular(final int[] nums) {
    int os = nums[0];
    int min = nums[0];
    int res = nums[0];
    int min2 = nums[0];
    int res2 = nums[0];
    for (int i = 1; i < nums.length; i++) {
      os += nums[i];
      min = Math.min(nums[i] + min, nums[i]);
      res = Math.min(res, min);
      min2 = Math.max(nums[i] + min2, nums[i]);
      res2 = Math.max(res2, min2);
    }
    if (os == res) {
      return res2;
    }
    return Math.max(os - res, res2);
  }

  /*
      Given an integer array nums and an integer k,
      return the number of non-empty subarrays that have a sum divisible by k.
      A subarray is a contiguous part of an array.
  */
  public int subarrayDivByK(final int[] nums, final int k) {
    final Map<Integer, Integer> map = new HashMap<>();
    int remainder;
    for (int i = 0; i < nums.length; i++) {
      if (i > 0) {
        nums[i] += nums[i - 1];
      }
      remainder = (nums[i] % k + k) % k;
      map.put(remainder, map.getOrDefault(remainder, 0) + 1);
    }
    int count = map.getOrDefault(0, 0);
    for (int frequency : map.values()) {
      count += frequency * (frequency - 1) / 2;
    }
    return count;
  }

  /*
      Given an integer array nums,
      return all the different possible non-decreasing subsequences of the given array
      with at least two elements. You may return the answer in any order.
  */
  public List<List<Integer>> findSubsequences(final int[] nums) {
    final List<List<Integer>> result = new ArrayList<>();
    int len = nums.length;
    int size;
    final HashSet<List<Integer>> set = new HashSet<>();
    for (int i = 0; i < (1 << len); i++) {
      List<Integer> list = new ArrayList<>();
      int a = i;
      for (final int num : nums) {
        size = list.size();
        if ((a & 1) == 1) {
          if (size >= 1 && num < list.get(size - 1)) {
            break;
          }
          list.add(num);
        }
        a = a >> 1;
        if (a == 0) {
          break;
        }
      }
      if (list.size() >= 2) {
        if (!set.contains(list)) {
          result.add(new ArrayList<>(list));
          set.add(new ArrayList<>(list));
        }
      }
    }
    return result;
  }

  /*
      Given an integer array nums, return the number of subarrays filled with 0.
      A subarray is a contiguous non-empty sequence of elements within an array.
   */
  public long zeroFilledSubarray(final int[] nums) {
    long count = 0;
    long result = 0;
    for (final int num : nums) {
      if (num != 0) {
        count = 0;
      } else {
        count++;
      }
      result += count;
    }
    return result;
  }
}
