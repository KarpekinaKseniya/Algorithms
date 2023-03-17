package org.example.nodes;

import java.util.HashSet;

/*
    A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store
    and retrieve keys in a dataset of strings. There are various applications of this data structure,
    such as autocomplete and spellchecker.
    Implement the Trie class:
        Trie() Initializes the trie object.
        void insert(String word) Inserts the string word into the trie.
        boolean search(String word) Returns true if the string word is
            in the trie (i.e., was inserted before), and false otherwise.
        boolean startsWith(String prefix) Returns true if there is a previously inserted string word
            that has the prefix, and false otherwise.
 */
class Trie {

    private HashSet<String> set = new HashSet<>();

    public Trie() {
    }

    public void insert(final String word) {
        set.add(word);
    }

    public boolean search(final String word) {
        if (set.contains(word))
            return true;
        return false;
    }

    public boolean startsWith(final String prefix) {
        for (final String word : set) {
            if (word.startsWith(prefix))
                return true;
        }
        return false;
    }
}