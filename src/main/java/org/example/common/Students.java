package org.example.common;

import java.util.LinkedList;
import java.util.Queue;

/*
    The school cafeteria offers circular and square sandwiches at lunch break,
    referred to by numbers 0 and 1 respectively. All students stand in a queue.
    Each student either prefers square or circular sandwiches.

    The number of sandwiches in the cafeteria is equal to the number of students.
    The sandwiches are placed in a stack. At each step:

    If the student at the front of the queue prefers the sandwich on the top of the stack,
    they will take it and leave the queue.
    Otherwise, they will leave it and go to the queue's end.
    This continues until none of the queue students want to take the top sandwich and
    are thus unable to eat.

    You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the
    i-th sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of
    the j-th student in the initial queue (j = 0 is the front of the queue).
    Return the number of students that are unable to eat.
*/
public class Students {

    public int countStudents(final int[] students, final int[] sandwiches) {
        final Queue<Integer> queue = new LinkedList<>();
        for (final int student : students) {
            queue.offer(student);
        }
        for (final int sandwich : sandwiches) {
            final int size = queue.size();
            int attempts = 0;
            while (!queue.isEmpty()) {
                if (queue.peek() == sandwich) {
                    queue.poll();
                    break;
                } else {
                    attempts++;
                    queue.offer(queue.poll());
                    if (size == attempts) {
                        return queue.size();
                    }
                }
            }
        }
        return 0;
    }
}
