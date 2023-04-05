package org.example.common;

import java.util.Arrays;

/*
  A chef has collected data on the satisfaction level of his n dishes.
  Chef can cook any dish in 1 unit of time.
  Like-time coefficient of a dish is defined as the time taken to cook that dish including
  previous dishes multiplied by its satisfaction level i.e. time[i] * satisfaction[i].
  Return the maximum sum of like-time coefficient that the chef can obtain after dishes preparation.
  Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.
*/
public class Dishes {

  public int maxSatisfaction(final int[] satisfaction) {
    Arrays.sort(satisfaction);
    int runningSum = 0;
    int result = 0;
    final int size = satisfaction.length;
    for (int i = size - 1; i >= 0 && satisfaction[i] + runningSum > 0; i--) {
      runningSum += satisfaction[i];
      result += runningSum;
    }
    return result;
  }
}
