package org.example.another;

/*
    An underground railway system is keeping track of customer travel times between different stations.
    They are using this data to calculate the average time it takes to travel  from one station to another

    Implement the UndergroundSystem class:
    void checkIn(int id, string stationName, int t)
    A customer with a card ID equal to id, checks in at the station stationName at time t.
    A customer can only be checked into one place at a time.
    void checkOut(int id, string stationName, int t)
    A customer with a card ID equal to id, checks out from the station stationName at time t.
    double getAverageTime(string startStation, string endStation)
    Returns the average time it takes to travel from startStation to endStation.
    The average time is computed from all the previous traveling times from startStation to
    endStation that happened directly, meaning a check in at startStation followed by a check out
    from endStation.
    The time it takes to travel from startStation to endStation may be different from the
    time it takes to travel from endStation to startStation.
    There will be at least one customer that has traveled from startStation to
    endStation before getAverageTime is called.
    You may assume all calls to the checkIn and checkOut methods are consistent.
    If a customer checks in at time t1 then checks out at time t2, then t1 < t2.
    All events happen in chronological order.


 */

import java.util.HashMap;
import java.util.Map;

class Passenger {
    int checkinTime;
    int checkoutTime;
    String checkinLocation;
    String checkoutLocation;

    public Passenger(String checkinLocation, int checkinTime) {
        this.checkinLocation = checkinLocation;
        this.checkinTime = checkinTime;
    }

    void checkout(String checkoutLocation, int checkoutTime) {
        this.checkoutLocation = checkoutLocation;
        this.checkoutTime = checkoutTime;
    }

}

class Route {
    private String startStation;
    private String endStation;
    private int totalNumberOfTrips;
    private long totalTimeSpentInTrips;

    public Route(final String startStation, final String endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }

    double getAverageTime() {
        return (double) totalTimeSpentInTrips / totalNumberOfTrips;
    }

    void addTrip(final int startTime, final int endTime) {
        totalTimeSpentInTrips += endTime - startTime;
        totalNumberOfTrips++;
    }
}

class UndergroundSystem {

    private Map<Integer, Passenger> currentPassengerMap;
    private Map<String, Route> routeMap;

    public UndergroundSystem() {
        currentPassengerMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(final int id, final String stationName, final int t) {
        if (!currentPassengerMap.containsKey(id)) {
            Passenger passenger = new Passenger(stationName, t);
            currentPassengerMap.put(id, passenger);
        }
    }

    public void checkOut(final int id, final String stationName, final int t) {
        if (currentPassengerMap.containsKey(id)) {
            Passenger passenger = currentPassengerMap.get(id);
            passenger.checkout(stationName, t);
            String routeKey = passenger.checkinLocation + "," + passenger.checkoutLocation;
            Route route = routeMap.getOrDefault(routeKey, new Route(passenger.checkinLocation, passenger.checkoutLocation));
            route.addTrip(passenger.checkinTime, passenger.checkoutTime);
            routeMap.put(routeKey, route);
            currentPassengerMap.remove(id);
        }
    }

    public double getAverageTime(final String startStation, final String endStation) {
        return routeMap.get(startStation + "," + endStation).getAverageTime();
    }
}
