package org.example.another;

import java.util.PriorityQueue;
import java.util.Queue;

/*
    Design a system that manages the reservation state of n seats that are numbered from 1 to n.
    Implement the SeatManager class:
        SeatManager(int n) Initializes a SeatManager object that will manage n seats numbered from 1 to n.
            All seats are initially available.
        int reserve() Fetches the smallest-numbered unreserved seat, reserves it, and returns its number.
        void unreserve(int seatNumber) Unreserves the seat with the given seatNumber.
*/
public class SeatManager {

    private Queue<Integer> seats;

    public SeatManager(int n) {
        seats = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            seats.offer(i);
        }
    }

    public int reserve() {
        return seats.poll();
    }

    public void unreserve(int seatNumber) {
        seats.offer(seatNumber);
    }

}
