package org.example.another;

import java.util.LinkedList;
import java.util.Queue;

/*
    Implement a last-in-first-out (LIFO) stack using only two queues.
    The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
    Implement the MyStack class:
        void push(int x) Pushes element x to the top of the stack.
        int pop() Removes the element on the top of the stack and returns it.
        int top() Returns the element on the top of the stack.
        boolean empty() Returns true if the stack is empty, false otherwise.
    Notes:
        You must use only standard operations of a queue, which means that only push to back,
        peek/pop from front, size and is empty operations are valid.
        Depending on your language, the queue may not be supported natively. You may simulate a queue
        using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
*/
public class MyStack {

    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    private int top;

    public MyStack() {
    }

    public void push(int x) {
        q1.offer(x);
        top = x;
    }

    public int pop() {
        while (q1.size() > 1) {
            top = q1.poll();
            q2.offer(top);
        }
        int val = q1.poll();
        while (!q2.isEmpty()) {
            q1.offer(q2.poll());
        }
        return val;
    }

    public int top() {
        // if(!q1.isEmpty())
        return top;
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}

