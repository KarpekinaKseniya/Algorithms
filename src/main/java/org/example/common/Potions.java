package org.example.common;

/*
    You are given two positive integer arrays spells and potions,
    of length n and m respectively, where spells[i] represents the strength
    of the i-th spell and potions[j] represents the strength of the j-th potion.

    You are also given an integer success. A spell and potion pair is considered successful
    if the product of their strengths is at least success.

    Return an integer array pairs of length n where pairs[i] is the number of potions
    that will form a successful pair with the i-th spell.
 */
public class Potions {

    public int[] successfulPairs(final int[] spells, final int[] potions, final long success) {
        int maxPotion = Integer.MIN_VALUE;
        for (final int potion : potions) {
            maxPotion = Math.max(potion, maxPotion);
        }
        int[] potionCount = new int[maxPotion + 1];
        for (final int potion : potions) {
            potionCount[potion]++;
        }
        for (int i = maxPotion - 1; i >= 0; i--) {
            potionCount[i] += potionCount[i + 1];
        }
        for (int i = 0; i < spells.length; i++) {
            final int requiredPotion = Math.toIntExact(Math.min((success + spells[i] - 1) / spells[i], 100001));
            spells[i] = requiredPotion > maxPotion ? 0 : potionCount[requiredPotion];
        }
        return spells;
    }
}
