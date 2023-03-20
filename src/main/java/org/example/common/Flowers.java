package org.example.common;

/*
    You have a long flowerbed in which some plots are planted, and some are not.
    However, flowers cannot be planted in adjacent plots.
    Given an integer array flowerbed containing 0's and 1's,
      where 0 means empty and 1 means not empty, and an integer n,
      return if n new flowers can be planted in the flowerbed
      without violating the no-adjacent-flowers rule.
*/
public class Flowers {

  public boolean canPlaceFlowers(int[] flowerbed, int n) {
    final int len = flowerbed.length;
    for (int i = 0; i < len; i += 2) {
      if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == len - 1
          || flowerbed[i + 1] == 0)) {
        flowerbed[i] = 1;
        n--;
      }
      if (n <= 0) {
        return true;
      }
    }
    return false;
  }
}
