package org.example.common;

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
public class Winner {

    public boolean PredictTheWinner(int[] nums) {
        if (nums.length == 1) return true;
        int su = 0;
        for (int i : nums) su += i;
        int res = ans(nums, 0, nums.length - 1);
        if (res < (su - res)) return false;
        return true;
    }

    public static int ans(int[] nums, int left, int right) {
        if (left > right) return 0;
        int choice1 = nums[left] + Math.min(ans(nums, left + 2, right), ans(nums, left + 1, right - 1));
        int choice2 = nums[right] + Math.min(ans(nums, left + 1, right - 1), ans(nums, left, right - 2));
        return Math.max(choice1, choice2);
    }

}
