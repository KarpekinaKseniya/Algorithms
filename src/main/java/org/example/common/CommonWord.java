package org.example.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
    Given a string paragraph and a string array of the banned words banned, return the most frequent word that is not banned.
    It is guaranteed there is at least one word that is not banned, and that the answer is unique.
    The words in paragraph are case-insensitive and the answer should be returned in lowercase.
 */
class CommonWord {

    private static final String PUNCTUATION_SYMBOLS = "[\\p{Punct}\\s]+";
    private static final int ONE = 1;

    public String mostCommonWord(final String paragraph, final String[] banned) {
        final String[] words = paragraph.split(PUNCTUATION_SYMBOLS);
        final Map<String, Integer> countWords = new HashMap<>();
        for (final String word : words) {
            final String lowerCaseWord = word.trim().toLowerCase();
            if (countWords.containsKey(lowerCaseWord)) {
                countWords.put(lowerCaseWord, countWords.get(lowerCaseWord) + ONE);
            } else {
                countWords.put(lowerCaseWord, ONE);
            }
        }
        final Optional<Map.Entry<String, Integer>> result =
                countWords.entrySet().stream().filter(value -> !Arrays.asList(banned).contains(value.getKey()))
                        .max(Map.Entry.comparingByValue());
        return result.map(Map.Entry::getKey).orElse(null);
    }
}
