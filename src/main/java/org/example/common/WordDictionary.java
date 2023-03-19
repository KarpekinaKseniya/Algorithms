package org.example.common;

/*
    Design a data structure that supports adding new words and finding if a string matches any
    previously added string.
    Implement the WordDictionary class:
        WordDictionary() Initializes the object.
        void addWord(word) Adds word to the data structure, it can be matched later.
        bool search(word) Returns true if there is any string in the data structure that matches
            word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 */
class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(final String word) {
        TrieNode curr = root;
        for (final char c : word.toCharArray()) {
            if (curr.getChildren()[c - 'a'] == null) {
                curr.getChildren()[c - 'a'] = new TrieNode();
            }
            curr = curr.getChildren()[c - 'a'];
        }
        curr.setEndOfWord(true);
    }

    public boolean search(final String word) {
        return searchHelper(word, 0, root);
    }

    private boolean searchHelper(final String word, final int index, final TrieNode curr) {
        if (index == word.length()) {
            return curr.isEndOfWord();
        }
        final char c = word.charAt(index);
        if (c == '.') {
            for (final TrieNode child : curr.getChildren()) {
                if (child != null && searchHelper(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            final TrieNode child = curr.getChildren()[c - 'a'];
            if (child == null) {
                return false;
            }
            return searchHelper(word, index + 1, child);
        }
    }
}

class TrieNode {
    private boolean isEndOfWord;
    private TrieNode[] children;

    public TrieNode() {
        isEndOfWord = false;
        children = new TrieNode[26];
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(final boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public void setChildren(final TrieNode[] children) {
        this.children = children;
    }
}