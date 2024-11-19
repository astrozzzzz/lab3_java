package ru.lazarev.city;

import java.util.*;

public class Route {
    private City startCity;
    private City endCity;

    public Route(City startCity, City endCity) {
        if (startCity == null || endCity == null) {
            throw new IllegalArgumentException("Start and End cities must not be null.");
        }
        this.startCity = startCity;
        this.endCity = endCity;
    }

    public City getStartCity() {
        return startCity;
    }

    public City getEndCity() {
        return endCity;
    }

    public void setStartCity(City startCity) {
        if (startCity == null) {
            throw new IllegalArgumentException("Start city must not be null.");
        }
        this.startCity = startCity;
    }

    public void setEndCity(City endCity) {
        if (endCity == null) {
            throw new IllegalArgumentException("End city must not be null.");
        }
        this.endCity = endCity;
    }

    public City[] getRoute() {
        List<City> routeList = new ArrayList<>();
        Set<City> visited = new HashSet<>();
        boolean pathFound = findRoute(startCity, endCity, routeList, visited);
        if (!pathFound) {
            return new City[0];
        }
        return routeList.toArray(new City[0]);
    }

    private boolean findRoute(City currentCity, City targetCity, List<City> routeList, Set<City> visited) {
        if (visited.contains(currentCity)) {
            return false;
        }
        routeList.add(currentCity);
        visited.add(currentCity);
        if (currentCity.equals(targetCity)) {
            return true;
        }
        for (Map.Entry<City, Integer> entry : currentCity.getPaths().entrySet()) {
            City nextCity = entry.getKey();
            if (findRoute(nextCity, targetCity, routeList, visited)) {
                return true;
            }
        }
        routeList.remove(currentCity);
        return false;
    }

    public String toString() {
        City[] routeArray = getRoute();
        String routeString = "";
        for (City city : routeArray) {
            routeString += city.getName() + " -> ";
        }
        if (routeString.length() > 0) {
            routeString = routeString.substring(0, routeString.length() - 4);
        }
        return routeString;
    }
}
