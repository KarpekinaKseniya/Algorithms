package org.example.another;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/*
    Design a food rating system that can do the following:
        Modify the rating of a food item listed in the system.
        Return the highest-rated food item for a type of cuisine in the system.
    Implement the FoodRatings class:
        FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system.
            The food items are described by foods, cuisines and ratings, all of which have a length of n.
        foods[i] is the name of the ith food,
        cuisines[i] is the type of cuisine of the ith food, and
        ratings[i] is the initial rating of the ith food.
        void changeRating(String food, int newRating) Changes the rating of the food item
            with the name food.
        String highestRated(String cuisine) Returns the name of the food item that has the highest
            rating for the given type of cuisine. If there is a tie, return the item with
            the lexicographically smaller name.
    Note that a string x is lexicographically smaller than string y if x comes before y in dictionary
    order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i],
    then x[i] comes before y[i] in alphabetic order.
*/
public class FoodRatings {

    Map<String, Integer> foodRatingMap;
    Map<String, String> foodCuisineMap;
    Map<String, TreeSet<Pair>> cuisineFoodMap;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodRatingMap = new HashMap<>();
        foodCuisineMap = new HashMap<>();
        cuisineFoodMap = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            foodRatingMap.put(foods[i], ratings[i]);
            foodCuisineMap.put(foods[i], cuisines[i]);

            cuisineFoodMap.putIfAbsent(cuisines[i], new TreeSet<>());
            cuisineFoodMap.get(cuisines[i]).add(new Pair(-ratings[i], foods[i]));
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodCuisineMap.get(food);
        int oldRating = foodRatingMap.get(food);

        cuisineFoodMap.get(cuisine).remove(new Pair(-oldRating, food));
        cuisineFoodMap.get(cuisine).add(new Pair(-newRating, food));

        foodRatingMap.put(food, newRating);
    }

    public String highestRated(String cuisine) {
        TreeSet<Pair> foods = cuisineFoodMap.get(cuisine);

        if (foods != null && !foods.isEmpty()) {
            return foods.first().second;
        }
        return null;
    }

    private static class Pair implements Comparable<Pair> {
        int first;
        String second;

        Pair(int first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.first != other.first) {
                return Integer.compare(this.first, other.first);
            } else {
                return this.second.compareTo(other.second);
            }
        }
    }
}
