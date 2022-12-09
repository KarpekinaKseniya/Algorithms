package org.example;

import java.util.HashSet;
import java.util.Set;

/*
    A pangram is a sentence where every letter of the English alphabet appears at least once.
    Given a string sentence containing only lowercase English letters,
    return true if sentence is a pangram, or false otherwise.
*/
public class Pangram {

    private static final String NON_ALPHABETS = "[^a-z]";
    private static final String EMPTY_STRING = "";
    private static final int ENGLISH_ALPHABET_LENGTH = 26;

    public boolean checkIfPangram(final String sentence) {
        final String checkSentence = sentence.toLowerCase().replaceAll(NON_ALPHABETS, EMPTY_STRING);
        final Set<Character> set = new HashSet<>();
        for (final char sym : checkSentence.toCharArray()) {
            set.add(sym);
        }
        return set.size() == ENGLISH_ALPHABET_LENGTH;
    }
}
