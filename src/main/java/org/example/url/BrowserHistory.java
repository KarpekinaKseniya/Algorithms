package org.example.url;

import java.util.ArrayList;
import java.util.List;

/*
    You have a browser of one tab where you start on the homepage and you can visit another url,
    get back in the history number of steps or move forward in the history number of steps.
    Implement the BrowserHistory class:
        BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
        void visit(string url) Visits url from the current page. It clears up all the forward history.
        string back(int steps) Move steps back in history. If you can only return x steps in the
            history and steps > x, you will return only x steps. Return the current url after moving b
            ack in history at most steps.
        string forward(int steps) Move steps forward in history. If you can only forward x steps in
            the history and steps > x, you will forward only x steps. Return the current url after
            forwarding in history at most steps.
 */
public class BrowserHistory {

    private List<String> list;
    private int total;
    private int curr;

    public BrowserHistory(final String homepage) {
        list = new ArrayList<>();
        list.add(homepage);
        total++;
        curr++;
    }

    public void visit(final String url) {
        if (list.size() > curr) {
            list.set(curr, url);
        } else {
            list.add(url);
        }
        curr++;
        total = curr;
    }

    public String back(final int steps) {
        curr = Math.max(1, curr - steps);
        return list.get(curr - 1);
    }

    public String forward(final int steps) {
        curr = Math.min(total, curr + steps);
        return list.get(curr - 1);
    }
}
