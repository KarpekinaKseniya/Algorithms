package org.example.common;

import java.util.ArrayList;
import java.util.List;

/*
    Given an array of strings words and a width maxWidth, format the text such that each line has
    exactly maxWidth characters and is fully (left and right) justified.
    You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
    Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
    Extra spaces between words should be distributed as evenly as possible. If the number of spaces on
    a line does not divide evenly between words, the empty slots on the left will be assigned more
    spaces than the slots on the right.
    For the last line of text, it should be left-justified, and no extra space is inserted between words.
    Note:
        A word is defined as a character sequence consisting of non-space characters only.
        Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
        The input array words contains at least one word.
*/
public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();
        int index = 0;
        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (words[last].length() + count + 1 > maxWidth) break;
                count += words[last].length() + 1;
                last++;
            }
            StringBuilder builder = new StringBuilder();
            int diff = last - index - 1;
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i] + " ");
                }
                builder.deleteCharAt(builder.length() - 1);
                builder.append(" ".repeat(Math.max(0, maxWidth - builder.length())));
            } else {
                int spaces = (maxWidth - count) / diff;
                int r = (maxWidth - count) % diff;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) {
                        builder.append(" ".repeat(Math.max(0, (spaces + ((i - index) < r ? 1 : 0)) + 1)));
                    }
                }
            }
            lines.add(builder.toString());
            index = last;
        }
        return lines;
    }

}
