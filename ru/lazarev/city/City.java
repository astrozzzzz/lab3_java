package ru.lazarev.city;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class City {
    private String name;
    private Map<City, Integer> paths;

    public City(String name) {
        this.name = name;
        this.paths = new HashMap<>();
    }

    public City(String name, Map<City, Integer> p) {
        this.name = name;
        this.paths = new HashMap<>();
        for (Map.Entry<City, Integer> entry : p.entrySet()) {
            addPath(entry.getKey(), entry.getValue());
        }
    }

    public String getName() {
        return name;
    }

    public Map<City, Integer> getPaths() {
        return this.paths;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPaths(Map<City, Integer> p) {
        this.paths.clear();
        for (Map.Entry<City, Integer> entry : p.entrySet()) {
            addPath(entry.getKey(), entry.getValue());
        }
    }

    public void addPath(City city, int cost) {
        if (!paths.containsKey(city) && cost != 0) {
            paths.put(city, cost);
        }
    }

    public void removePath(City city) {
        paths.remove(city);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Город: ").append(this.name).append("\n");
        res.append("Пути: \n");
        for (Map.Entry<City, Integer> entry : paths.entrySet()) {
            res.append(entry.getKey().name).append(": ").append(entry.getValue()).append("\n");
        }
        return res.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(paths, city.paths);
    }
}