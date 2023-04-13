package org.example.common;

/*
    Given two integer arrays pushed and popped each with distinct values,
    return true if this could have been the result of a sequence of push and pop operations
    on an initially empty stack, or false otherwise.
*/
public class Sequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int pushedI = 0;
        int poppedI = 0;
        for (final int elm : pushed) {
            pushed[pushedI++] = elm;
            while (pushedI != 0 && popped[poppedI] == pushed[pushedI - 1]) {
                poppedI++;
                pushedI--;
            }
        }
        return pushedI == 0;
    }
}
