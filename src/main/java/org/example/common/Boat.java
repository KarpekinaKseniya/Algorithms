package org.example.common;

import java.util.Arrays;

/*
    You are given an array people where people[i] is the weight of the ith person,
    and an infinite number of boats where each boat can carry a maximum weight of limit.
    Each boat carries at most two people at the same time, provided the sum of the weight
    of those people is at most limit.

    Return the minimum number of boats to carry every given person.
*/
public class Boat {

  public int numRescueBoats(final int[] people, final int limit) {
    int count = 0;
    Arrays.sort(people);
    int index = 0;
    int number = people.length - 1;
    while (index <= number) {
      if (people[index] + people[number] <= limit) {
        index++;
      }
      number--;
      count++;
    }
    return count;
  }
}
