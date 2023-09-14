package org.example.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
    You are given a list of airline tickets where tickets[i] = [fromi, toi] represent
    the departure and the arrival airports of one flight. Reconstruct the itinerary in order
    and return it.
    All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin
    with "JFK". If there are multiple valid itineraries, you should return the itinerary that
    has the smallest lexical order when read as a single string.
    For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
    You may assume all tickets form at least one valid itinerary. You must use all the tickets once
    and only once.
*/
public class Itinerary {

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (int i = 0; i < tickets.size(); i++) {
            if (!map.containsKey(tickets.get(i).get(0))) {
                PriorityQueue<String> pq = new PriorityQueue<>();
                map.put(tickets.get(i).get(0), pq);
            }
            map.get(tickets.get(i).get(0)).add(tickets.get(i).get(1));
        }
        LinkedList<String> list = new LinkedList<>();
        dfs("JFK", map, list);
        return list;
    }

    private void dfs(String s, Map<String, PriorityQueue<String>> map, LinkedList<String> list) {
        PriorityQueue<String> pq = map.get(s);
        while (pq != null && !pq.isEmpty()) {
            dfs(pq.poll(), map, list);

        }
        list.addFirst(s);
    }

}
