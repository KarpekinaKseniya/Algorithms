package org.example.common;

public class Winner {

    /*
        Given an integer array arr of distinct integers and an integer k.
        A game will be played between the first two elements of the array (i.e. arr[0] and arr[1]).
        In each round of the game, we compare arr[0] with arr[1], the larger integer wins and remains
        at position 0, and the smaller integer moves to the end of the array. The game ends when
        an integer wins k consecutive rounds.
        Return the integer which will win the game.
        It is guaranteed that there will be a winner of the game.
    */
    public int getWinner(int[] arr, int k) {
        int current = arr[0];
        int consecutiveWins = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > current) {
                current = arr[i];
                consecutiveWins = 1;
            } else {
                consecutiveWins++;
            }
            if (consecutiveWins == k) {
                return current;
            }
        }
        return current;
    }

    /*
        You are given an integer array nums. Two players are playing a game with this array:
        player 1 and player 2.
        Player 1 and player 2 take turns, with player 1 starting first. Both players start
        the game with a score of 0. At each turn, the player takes one of the numbers from
        either end of the array (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of
        the array by 1. The player adds the chosen number to their score. The game ends when there are
        no more elements in the array.
        Return true if Player 1 can win the game. If the scores of both players are equal, then player 1
        is still the winner, and you should also return true. You may assume that both players are playing
        optimally.
    */
    public boolean PredictTheWinner(int[] nums) {
        if (nums.length == 1) return true;
        int su = 0;
        for (int i : nums) su += i;
        int res = ans(nums, 0, nums.length - 1);
        return res >= (su - res);
    }

    private int ans(int[] nums, int left, int right) {
        if (left > right) return 0;
        int choice1 = nums[left] + Math.min(ans(nums, left + 2, right), ans(nums, left + 1, right - 1));
        int choice2 = nums[right] + Math.min(ans(nums, left + 1, right - 1), ans(nums, left, right - 2));
        return Math.max(choice1, choice2);
    }

}
