package ru.lazarev.city;

import java.util.Map;

public class BidirectionalCity extends City {

    public BidirectionalCity(String name) {
        super(name);
    }

    public BidirectionalCity(String name, Map<City, Integer> p) {
        super(name);
        for (Map.Entry<City, Integer> entry : p.entrySet()) {
            this.addPath(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void addPath(City city, int cost) {
        if (!this.getPaths().containsKey(city)) {
            super.addPath(city, cost);
            if (!city.getPaths().containsKey(this)) {
                city.addPath(this, cost);
            }
        }
    }

    @Override
    public void removePath(City city) {
        if (this.getPaths().containsKey(city)) {
            super.removePath(city);
            if (city.getPaths().containsKey(this)) {
                city.removePath(this);
            }
        }
    }
}
